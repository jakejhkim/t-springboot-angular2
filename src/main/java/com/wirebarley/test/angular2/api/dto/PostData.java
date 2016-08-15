package com.wirebarley.test.angular2.api.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jake on 2016. 8. 15..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostData {
    String title;
    String content;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class PostResData extends PostData {
        Long id;
        Date created;
        Date lastModified;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostListResData {
        List<PostResData> list;
    }
}
