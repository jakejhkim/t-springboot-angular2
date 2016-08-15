package com.wirebarley.test.angular2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jake on 2016. 8. 15..
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    String title;

    String content;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date lastModified;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date created;
}
