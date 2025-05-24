package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {

	@Insert("""
			INSERT INTO article
			    SET regDate = NOW()
			        , updateDate = NOW()
			        , memberId = #{loginedMemberId}
			        , boardId = #{boardId}
			        , title = #{title}
			        , content = #{content}
			""")
	public void writeArticle(String title, String content, int loginedMemberId, int boardId);

	@Select("""
			<script>
			SELECT a.*, m.loginId
			    FROM article a
			    INNER JOIN `member` m
			    ON a.memberId = m.id
			    WHERE boardId = #{boardId}
			    		<if test="'titleContent' == searchType">
			    			AND title LIKE CONCAT('%', #{keyWord}, '%') OR content LIKE CONCAT('%', #{keyWord}, '%')
			    		</if>
			    		<if test="'title' == searchType">
			    			AND title LIKE CONCAT('%', #{keyWord}, '%')
			    		</if>
			    		<if test="'content' == searchType">
			    			AND content LIKE CONCAT('%', #{keyWord}, '%')
			    		</if>
				ORDER BY a.id DESC
				LIMIT #{limitFrom}, #{articlesInPage}
				</script>
			""")
	public List<Article> getArticles(String keyWord, String searchType, int boardId, int articlesInPage, int limitFrom);

	@Select("""
			<script>
			SELECT COUNT(id)
			FROM article
			WHERE boardId = #{boardId}
					<if test="'titleContent' == searchType">
		    			AND title LIKE CONCAT('%', #{keyWord}, '%') OR content LIKE CONCAT('%', #{keyWord}, '%')
		    		</if>
		    		<if test="'title' == searchType">
		    			AND title LIKE CONCAT('%', #{keyWord}, '%')
		    		</if>
		    		<if test="'content' == searchType">
		    			AND content LIKE CONCAT('%', #{keyWord}, '%')
		    		</if>
		    	</script>
			""")
	public int getArticlesCnt(int boardId, String keyWord, String searchType);
	
	@Insert("""
			INSERT INTO likes
				SET memberId = #{memberId}
				, relId = #{relId}
				, relTypeCode = #{relTypeCode}
			""")
	public int getArticleslike(int memberId, int relId, String relTypeCode);
	
	@Select("""
			SELECT a.*, m.loginId, 
			       (SELECT COUNT(*) 
			        FROM likes 
			        WHERE relId = #{id}
					AND relTypeCode = #{relTypeCode}) AS likesCnt
				FROM article a
				INNER JOIN member m 
				ON m.id = a.memberId
				WHERE a.id = #{id}
			""")
	public Article getArticle(int id, String relTypeCode);
	
	@Select("""
			SELECT a.*, m.loginId
			 	FROM article a
			 	INNER JOIN `member` m
			 	ON a.memberId = m.id
			 	WHERE a.id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
			UPDATE article
			    SET updateDate = NOW()
			    	<if test="title != null and title != ''">
			        	, title = #{title}
			        </if>
			        <if test="content != null and content != ''">
			        	, content = #{content}
			        </if>
			    WHERE id = #{id}
			   </script>
			""")
	public void modifyArticle(int id, String title, String content);

	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastArticleId();

}