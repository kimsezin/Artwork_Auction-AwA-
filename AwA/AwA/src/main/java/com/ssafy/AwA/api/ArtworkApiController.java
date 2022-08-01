package com.ssafy.AwA.api;

import com.ssafy.AwA.domain.artwork.Artwork;
import com.ssafy.AwA.dto.*;
import com.ssafy.AwA.repository.ArtworkRepository;
import com.ssafy.AwA.repository.ProfileRepository;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artwork")
public class ArtworkApiController {

    private final Logger logger = LoggerFactory.getLogger(ArtworkApiController.class);

    private final ArtworkService artworkService;
    private final ArtworkRepository artworkRepository;

    private final ProfileRepository profileRepository;


    @PostMapping
    public ResponseEntity<ArtworkResponseDto> createArtwork(@RequestBody @Valid ArtworkRequestDto artworkRequestDto, @RequestHeader(value="X-AUTH-TOKEN") String token, @RequestHeader(value="RefreshToken") String refreshToken ) {
        ArtworkResponseDto artworkResponseDto = artworkService.saveArtwork(artworkRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(artworkResponseDto);
    }

    @GetMapping
    public List<ArtworkResponseDto> getAllArtwork() {
        List<ArtworkResponseDto> artworkResponseDto = artworkService.getAllArtwork();

        return artworkResponseDto;
    }

    @GetMapping("/{artwork_id}")
    public ArtworkResponseDto getArtworkById(@PathVariable("artwork_id") Long artwork_id) {
        ArtworkResponseDto artworkResponseDto = artworkService.getArtworkById(artwork_id);
        return artworkResponseDto;
    }

    @PutMapping("/{artwork_id}")
    public ArtworkResponseDto updateArtwork(@PathVariable("artwork_id") Long artwork_id, @RequestBody @Valid ArtworkRequestDto artworkRequestDto, @RequestHeader(value="X-AUTH-TOKEN") String token, @RequestHeader(value="RefreshToken") String refreshToken) {
        ArtworkResponseDto artworkResponseDto = artworkService.updateArtwork(artwork_id, artworkRequestDto);
        return artworkResponseDto;
    }

    @DeleteMapping("{artwork_id}")
    public int deleteArtwork(@PathVariable("artwork_id") Long artwork_id, @RequestHeader(value="X-AUTH-TOKEN") String token, @RequestHeader(value="RefreshToken") String refreshToken) {
        return artworkService.deleteArtwork(artwork_id);
    }
//    @PutMapping("/{artwork_id}/title/{newTitle}")
//    public int updateArtworkTitle(@PathVariable("artwork_id") Long artwork_id,@PathVariable("newTitle") String newTitle) {
//        artworkService.updateTitle(artwork_id, newTitle);
//        Artwork targetArtwork = artworkRepository.findByArtwork_id(artwork_id);
//
//        if(targetArtwork.getTitle().equals(newTitle))
//            return 1;
//        else
//            return 0;
//    }
//
//    //좋아요도 따로 테이블 빼자
//
//    @PutMapping("/{artwork_id}/price/{newPrice}")
//    public int updateArtworkPrice(@PathVariable("artwork_id") Long artwork_id, @PathVariable("newPrice") int newPrice) {
//        artworkService.updatePrice(artwork_id, newPrice);
//        Artwork targetArtwork = artworkRepository.findByArtwork_id(artwork_id);
//
//        if(targetArtwork.getPrice()==newPrice)
//            return 1;
//        else
//            return 0;
//    }
//
//    @PutMapping("/{artwork_id}/description")
//    public int updateArtworkDescription(@PathVariable("artwork_id") Long artwork_id, @RequestBody @Valid DescriptionDto descriptionDto) {
//        artworkService.updateDescription(artwork_id, descriptionDto.getDescription());
//        Artwork targetArtwork = artworkRepository.findByArtwork_id(artwork_id);
//
//        if(targetArtwork.getDescription().equals(descriptionDto.getDescription()))
//            return 1;
//        else
//            return 0;
//    }
//
//    @PutMapping("/{artwork_id}/genre")
//    public int updateArtworkGenre(@PathVariable("artwork_id") Long artwork_id, @RequestBody @Valid GenreDto genreDto) {
//        artworkService.updateGenre(artwork_id, genreDto.getGenre());
//        Artwork targetArtwork = artworkRepository.findByArtwork_id(artwork_id);
//
//
//        for(int i=0;i<targetArtwork.getGenre().size(); i++) {
//            if(!(genreDto.getGenre().get(i).equals(targetArtwork.getGenre().get(i))))
//                return 0;
//        }
//        return 1;
//    }
//
//    @PutMapping("/{artwork_id}/ingredient")
//    public int updateArtworkGenre(@PathVariable("artwork_id") Long artwork_id, @RequestBody @Valid IngredientDto ingredientDto) {
//        artworkService.updateIngredient(artwork_id, ingredientDto.getIngredient());
//        Artwork targetArtwork = artworkRepository.findByArtwork_id(artwork_id);
//
//        for(int i=0;i<targetArtwork.getIngredient().size();i++)
//            if(!(ingredientDto.getIngredient().get(i).equals(targetArtwork.getIngredient().get(i)))) {
//                return 0;
//            }
//        return 1;
//    }

//
}
