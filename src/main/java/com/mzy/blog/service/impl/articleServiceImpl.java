package com.mzy.blog.service.impl;

import com.mzy.blog.bean.Article;
import com.mzy.blog.bean.Tags;
import com.mzy.blog.mapper.articleMapper;
import com.mzy.blog.mapper.tagsMapper;
import com.mzy.blog.service.articleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class articleServiceImpl implements articleService {

    @Autowired
    articleMapper articleMapper;

    @Autowired
    tagsMapper tagsMapper;

    @Override
    public List<Article> getArticleByPageNum(int pageNum, int eachpagecount) {

        List<Article> articleList = articleMapper.selectAll();
        //articleslen文章总数  pageNum页数 eachpagecount每页个数
        int articleslen = articleList.size();
        if (articleslen == 0) return articleList;
        else {
            //如果每页文章数大于对应总数 那么返回全部 不解释
            if (eachpagecount > articleslen) {
                Collections.sort(articleList, new comparearticle());
                for (Article article : articleList) {
                    String tagString = article.getArticleTags();
                    String[] split = tagString.split(",");
                    List<Tags> tagsList = new ArrayList<>();
                    for (String s : split) {
                        Tags t = new Tags();
                        t.setTagName(s);
                        tagsList.add(t);

                    }
                    article.setTagsList(tagsList);


                }


                return articleList;
            } else {
                //因为每页小于总数 ，所以可以分页了
                int pageNumAll = articleslen / eachpagecount + articleslen % eachpagecount;
                //如果需要分的小于总的，那么正常
                if (pageNum <= pageNumAll) {
                    List<Article> articleres = new ArrayList<>();
                    Collections.sort(articleList, new comparearticle());
                    for (int i = eachpagecount * (pageNum - 1); i < articleList.size() && i <= eachpagecount * (pageNum) - 1; i++) {
                        Article article = articleList.get(i);

                        String tagString = article.getArticleTags();
                        String[] split = tagString.split(",");
                        List<Tags> tagsList = new ArrayList<>();
                        for (String s : split) {
                            Tags t = new Tags();
                            t.setTagName(s);
                            tagsList.add(t);

                        }
                        article.setTagsList(tagsList);

                        articleres.add(article);

                    }
                    return articleres;

                } else {
                    //否则空
                    return null;
                }
            }


        }


    }

    @Override
    public List<Article> getArticleAll() {
        return articleMapper.selectAll();
    }

    //根据特定的文章id来获取
    @Override
    public Article getArticleById(int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        Article articleres = articleMapper.selectOne(article);
        return articleres;
    }

    @Override
    public List<Article> getArticleByCateName(String categoryName) {

        List<Article> articleList = articleMapper.getArticleByCateName(categoryName);
        return articleList;
    }

    @Override
    public List<Article> getArticlesByTagName(String tagName) {
        List<Article> articleList = articleMapper.getArticlesByTagName(tagName);


        return articleList;
    }

    @Override
    //将文章信息加载至数据库
    public Boolean insertArticle(Article article) {


        Random random = new Random();
        List<String> tagsList = new ArrayList<>();
        List<String> publishTags = article.getArticleTagsValue();

        for (Tags tag : tagsMapper.selectAll()) {
            tagsList.add(tag.getTagName());
        }

        //tag处理
        for (String publishTag : publishTags) {
            //如果原来数据库不存在的话 ，新加入数据库
            if (!tagsList.contains(publishTag)) {
                Tags tag = new Tags();
                tag.setTagName(publishTag);
                tag.setTagSize(10 + random.nextInt(10));
                tag.setTagArticle(String.valueOf(article.getArticleId()));
                try {
                    tagsMapper.insert(tag);
                } catch (Exception e) {
                    System.out.println("tag插入错误");
                    return false;
                }
            } else {
                //追加id
                Tags tag = new Tags();
                tag.setTagName(publishTag);
                try {
                    Example example = new Example(Tags.class);
                    Tags tags = tagsMapper.selectOne(tag);
                    example.createCriteria().andEqualTo("tagArticle", tags.getTagArticle());
                    tags.setTagArticle(tags.getTagArticle() + "," + article.getArticleId());
                    tags.setId(null);
                    tagsMapper.updateByExampleSelective(tags, example);
                } catch (Exception e) {
                    System.out.println("tag更新错误");
                    return false;
                }
            }

        }

        //分类无需加入
        //文章加入
        List<String> publishTag = article.getArticleTagsValue();
        String join = StringUtils.join(publishTag, ",");

        article.setArticleTags(join);
        article.setArticleTabloid(article.getArticleContent().substring(0, article.getArticleContent().length() > 150 ? 150 : article.getArticleContent().length()));
        article.setAuthor(article.getAuthor() == null ? "mzy" : article.getAuthor());

        //返回日期
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(date);
        article.setPublishDate(nyr);
        article.setUpdateDate(nyr);
        article.setVisitor(0);
        articleMapper.insert(article);

        return true;
    }


    class comparearticle implements Comparator<Article> {

        @Override
        public int compare(Article a, Article b) {
            if (a.getArticleId().equals(b.getArticleId())) return 0;
            else {

                return a.getArticleId() > b.getArticleId() ? -1 : 1;

            }


        }
    }
}
