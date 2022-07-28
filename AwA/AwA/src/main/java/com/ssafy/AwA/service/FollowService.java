package com.ssafy.AwA.service;

import com.ssafy.AwA.domain.follow.Follow;
import com.ssafy.AwA.domain.profile.Profile;
import com.ssafy.AwA.repository.FollowRepository;
import com.ssafy.AwA.repository.ProfileRepository;
import com.ssafy.AwA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;
    //fromEmail->toEmail 팔로우

    public Follow save(String fromNickname, String toNickname) {
        Profile fromProfile = profileRepository.findByNickname(fromNickname);
        Profile toProfile = profileRepository.findByNickname(toNickname);

        Follow newFollow = Follow.builder()
                        .fromProfile(fromProfile)
                        .toProfile(toProfile)
                        .build();

        return followRepository.save(newFollow);
    }

    public Follow getFollowIdByFromUserEmailToUserEmail(String fromNickname, String toNickname) {


        Profile fromProfile = profileRepository.findByNickname(fromNickname);
        Profile toProfile = profileRepository.findByNickname(toNickname);

        return followRepository.findByFromUserAndToUser(fromProfile,toProfile);

    }
}

