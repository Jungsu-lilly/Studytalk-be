package com.planner.server.domain.friend.dto;

import com.planner.server.domain.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FindFriendByUserResDto {
    private String userProfileName;
    private int count;
    private List<UserDto> friend = new ArrayList<>();


    public static FindFriendByUserResDto toDto(String profileName, List<FriendDto> friendDtos){

        List<UserDto> temp = new ArrayList<>();
        friendDtos.stream().forEach(friendDto -> temp.add(friendDto.getFriend()));

        return FindFriendByUserResDto.builder()
                .userProfileName(profileName)
                .count(temp.size())
                .friend(temp)
                .build();
    }
}
