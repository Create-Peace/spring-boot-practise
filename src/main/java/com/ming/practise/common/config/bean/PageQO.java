package com.ming.practise.common.config.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageQO {

  public static final int DEFAULT_PAGE_SIZE = 10;

  private int currentPage = 1;

  private int pageSize = DEFAULT_PAGE_SIZE;

  public PageRequest getJpaPageParam() {
    return PageRequest.of(currentPage - 1, pageSize);
  }

  public PageRequest getJpaPageParam(Sort sort) {
    return PageRequest.of(currentPage - 1, pageSize, sort);
  }

  @Override
  public String toString() {
    return "PageQO{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + '}';
  }
}
