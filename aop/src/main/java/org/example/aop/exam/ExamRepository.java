package org.example.aop.exam;

import org.example.aop.exam.annotation.Retry;
import org.example.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

  private static int seq = 0;

  /**
   * 5번째 1번 실패하는 요청
   */
  @Trace
  @Retry(value = 4)
  public String save(String itemId) {
    seq++;
    if (seq % 5 == 0) {
      throw new IllegalStateException("예외 발생");
    }
    return "ok";
  }
}
