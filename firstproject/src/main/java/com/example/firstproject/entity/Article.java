package com.example.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스로 테이블을 만든다 DB가 객체를 인식하게함
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {

	@Id //id값을 부여하기위한 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	public void patch(Article article) {
		if(article.title != null)
			this.title = article.title;
		if(article.content != null)
			this.content = article.content;
	}
}
