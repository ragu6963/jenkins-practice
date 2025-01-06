import React from "react";
import styles from "./Article.module.css";
import { useNavigate } from "react-router-dom";

export default function Article({ article, isDetail }) {
  const Navigate = useNavigate();

  return (
    <div className={styles.articlesContainer}>
      <h2
        onClick={() => {
          Navigate(`/article/${article.id}`);
        }}
        className={`${styles.articleTitle} ${!isDetail && styles.pointer}`}
      >
        {article.title}
      </h2>
      <p className={styles.articleContent}>{article.content}</p>
    </div>
  );
}
