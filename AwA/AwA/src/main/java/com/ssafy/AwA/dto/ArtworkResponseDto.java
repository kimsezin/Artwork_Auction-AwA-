package com.ssafy.AwA.dto;

import com.ssafy.AwA.domain.attachment.Attachment;
import com.ssafy.AwA.domain.user.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ArtworkResponseDto {

    private Long artwork_id;

    private String sell_user;

    private String title;

    private int price;

    private List<AttachmentRequestDto> attachmentRequestDtoList;

    private List<String> genre;

    private List<String> ingredient;

    @Builder
    public ArtworkResponseDto(Long artwork_id, String sell_user, String title, int price, List<AttachmentRequestDto> attachmentRequestDtoList
                              ,List<String> genre, List<String> ingredient
    ) {
        this.artwork_id = artwork_id;
        this.sell_user = sell_user;
        this.title = title;
        this.price = price;
        this.attachmentRequestDtoList = attachmentRequestDtoList;
        this.genre = genre;
        this.ingredient = ingredient;
    }
}