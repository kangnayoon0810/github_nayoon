package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(String title, String content, int loginedMemberId, int boardId) {
		this.articleDao.writeArticle(title, content, loginedMemberId, boardId);
	}

	public List<Article> getArticles(String keyWord, String searchType, int boardId, int articlesInPage, int limitFrom) {
		return this.articleDao.getArticles(keyWord, searchType, boardId, articlesInPage, limitFrom);
	}

	public int getArticlesCnt(int boardId, String keyWord, String searchType) {
		return this.articleDao.getArticlesCnt(boardId, keyWord, searchType);
	}
	
	public int getArticleslike(int memberId, int relId, String relTypeCode) {
		return this.articleDao.getArticleslike(memberId, relId, relTypeCode);
	}
	
	public Article getArticle(int id, String relTypeCode) {
		return this.articleDao.getArticle(id, relTypeCode);
	}
	
	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyArticle(int id, String title, String content) {
		this.articleDao.modifyArticle(id, title, content);
	}

	public void deleteArticle(int id) {
		this.articleDao.deleteArticle(id);
	}

	public int getLastArticleId() {
		return this.articleDao.getLastArticleId();
	}

}