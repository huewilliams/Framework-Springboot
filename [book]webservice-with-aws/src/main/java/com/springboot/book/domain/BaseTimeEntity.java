package com.springboot.book.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 들이 이 클래스를 상속할 경우 필드들도 칼럼으로 인식하게함.
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함시킴.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성될 때 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate // Entity 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime updatedDate;
}
