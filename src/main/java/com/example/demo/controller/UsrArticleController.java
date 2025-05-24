package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.LoginedMember;
import com.example.demo.dto.Req;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {

	private ArticleService articleService;
	private BoardService boardService;
	private Req req;

	public UsrArticleController(ArticleService articleService, BoardService boardService, Req req) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.req = req;
	}

	@GetMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String content, int boardId) {

		this.articleService.writeArticle(title, content, this.req.getLoginedMember().getId(), boardId);

		int id = this.articleService.getLastArticleId();

		return Util.jsReplace("게시물이 등록되었습니다", String.format("detail?id=%d", id));
	}

	@GetMapping("/usr/article/list")
	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int cPage, @RequestParam(defaultValue = "") String keyWord, @RequestParam(defaultValue = "") String searchType) {
		
		int articlesInPage = 10;
		int limitFrom = (cPage - 1) * articlesInPage;
		
		int articlesCnt = this.articleService.getArticlesCnt(boardId, keyWord, searchType);
		
		int pagesCnt = (int) Math.ceil(articlesCnt / (double) articlesInPage);
		
		int startPage = ((cPage - 1) / 10) * 10 + 1;
		int endPage = (((cPage - 1) / 10) + 1 ) * 10;
		
		if (endPage > pagesCnt) {
			endPage = pagesCnt;
		}
		
		Board board = this.boardService.getBoard(boardId);
		List<Article> articles = this.articleService.getArticles(keyWord, searchType, boardId, articlesInPage, limitFrom);

		model.addAttribute("keyWord", keyWord);
		model.addAttribute("searchType", searchType);
		model.addAttribute("cPage", cPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("pagesCnt", pagesCnt);

		return "usr/article/list";
	}

	@GetMapping("/usr/article/detail")
	public Object detail(Model model, int id) {

		Article article = this.articleService.getArticle(id, "article");

		model.addAttribute("article", article);

		return "usr/article/detail";
	}
	
	@GetMapping("/usr/article/doLike")
	@ResponseBody
	public ResultData doLike(Model model, int id) {
		
		int likesCnt = this.articleService.getArticleslike(this.req.getLoginedMember().getId(), id, "article");
		
//		if (likesCnt == 1) {
//			
//		}
		
		return ResultData.from("S-1", "likeChk");
	}

	@GetMapping("/usr/article/modify")
	public String modify(Model model, int id) {

		Article article = this.articleService.getArticleById(id);

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String content) {

		this.articleService.modifyArticle(id, title, content);

		return Util.jsReplace(String.format("%d번 게시물이 수정되었습니다", id), String.format("detail?id=%d", id));
	}

	@GetMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {

		this.articleService.deleteArticle(id);

		return Util.jsReplace(String.format("%d번 게시물이 삭제되었습니다", id), "list");
	}
}