package com.ssafy.AwA.api;

import com.ssafy.AwA.dto.ArtworkRequestDto;
import com.ssafy.AwA.dto.ArtworkResponseDto;
import com.ssafy.AwA.service.ArtworkService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artwork")
public class ArtworkApiController {

    private final Logger logger = LoggerFactory.getLogger(ArtworkApiController.class);

    private final ArtworkService artworkService;

//    @ApiImplicitParams({
//            @ApiImplicitParam(name="X-AUTH-TOKEN", value = "로그인 성공 후 발급받은 access-token", required = true, dataType = "String", paramType = "header")
//    }) //헤더에 accesstoken 넣어서 보내야함 -> 로그인한 사용자만 글 쓰기 가능
    @PostMapping
    public ResponseEntity<ArtworkResponseDto> createArtwork(@RequestBody @Valid ArtworkRequestDto artworkRequestDto) {
        ArtworkResponseDto artworkResponseDto = artworkService.saveArtwork(artworkRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(artworkResponseDto);
    }

//    @GetMapping("/{userEmail}")
//    public ResponseEntity<ArtworkResponseDto> findArtworkByEmail(@PathVariable(name = "userEmail") String userEmail) {
//
//    }

}