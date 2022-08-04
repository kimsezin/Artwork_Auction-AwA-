const HOST = "http://i7c101.p.ssafy.io:8080/";

const AUTH = "auth/";
const NOTICE = "notice/";
const ARTWORK = "artwork/";
const PROFILE = "profile/";
const RANK = "rank/";
const FOLLOW = "follow/";
const LIKE = "like/";

const rf = {
  auth: {
    login: () => HOST + AUTH + "sign-in",
    logout: () => HOST + AUTH + "logout",
    signup: () => HOST + AUTH + "sign-up",
    userinfo: () => HOST + AUTH + "userinfo",
  },

  notice: {
    create: () => HOST + NOTICE,
    readOrUpdateOrDelete: (noticeId: string) => HOST + NOTICE + `${noticeId}`,
    readAll: () => HOST + NOTICE + "list",
  },

  artwork: {
    readAllOrPost: () => HOST + ARTWORK,
    readDetailOrUpdateOrDelete: (artwork_id: string) =>
      HOST + ARTWORK + `${artwork_id}`,
  },

  profile: {
    getOrUpdateProfile: (userEmail: string) => HOST + PROFILE + `${userEmail}`,
  },

  follow: {
    checkFollow: (fromUserEmail: string, toUserEmail: string) =>
      HOST + FOLLOW + "have/" + `${fromUserEmail}/${toUserEmail}`,
    followUser: (fromUserEmail: string, toUserEmail: string) =>
      HOST + FOLLOW + `${fromUserEmail}/${toUserEmail}`,
  },

  rank: {
    getLikeRank: () => HOST + RANK + "like",
  },

  like: {
    likeArtwork: (nickname: string, artwork_id: string) =>
      HOST + LIKE + `${nickname}/${artwork_id}`,
  },
};

export default rf;