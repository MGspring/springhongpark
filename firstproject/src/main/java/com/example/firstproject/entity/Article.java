package com.example.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Article {

	@Id //id값을 부여하기위한 어노테이션
	@GeneratedValue // 1,2,3 자동으로 부여해줌
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

}
