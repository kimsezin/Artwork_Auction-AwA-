import { useEffect, useState } from "react";
import api from "./../../api/api";
import { ArtworkItem, User } from "../../Interface";
import RankLike from "../RankLike";
import style from "./LoggedInHome.module.css";
import { useDispatch, useSelector } from "react-redux";
import { loadingActions } from "./../../store";
import { Masonry } from "@mui/lab";
import AuctionCard from "../../component/AuctionCard";
import { useInView } from "react-intersection-observer";

function LoggedInHome() {
  const dispatch = useDispatch();
  const loading = useSelector((state: { loading: boolean }) => state.loading);
  const userObject = useSelector(
    (state: { userObject: User }) => state.userObject
  );

  const [ref, inView] = useInView();
  const [items, setItems] = useState<ArtworkItem[]>([]);

  const getOnlyFollowItems = async () => {
    dispatch(loadingActions.toggle());

    try {
      const response = await api.artwork.getFollowingItems(userObject.email, 1);
      setItems((prev) => prev.concat(response.data.artworkResponseDto));
      setTimeout(() => {
        dispatch(loadingActions.toggle());
      }, 1000);
    } catch (err) {
      console.error(err);
      alert("정보를 불러오는데 실패했습니다.");
      dispatch(loadingActions.toggle());
    }
  };

  const getRecommends = async () => {
    dispatch(loadingActions.toggle());

    try {
      const response = await api.artwork.getRecommends(userObject.email);
      setItems((prev) => prev.concat(response.data.artworkResponseDto));
      setTimeout(() => {
        dispatch(loadingActions.toggle());
      }, 1000);
    } catch (err) {
      console.error(err);
      alert("정보를 불러오는데 실패했습니다.");
      dispatch(loadingActions.toggle());
    }
  };

  useEffect(() => {
    /* eslint-disable */
    getOnlyFollowItems();
  }, []);

  useEffect(() => {
    if (inView && !loading) {
      /* eslint-disable */
      getRecommends();
    }
  }, [inView, loading]);

  return (
    <>
      <div className={style.Body}>
        <div className={style.feed}>
          {items && (
            <Masonry columns={{ sm: 2, md: 3, xl: 4 }} spacing={1}>
              {items.map((item, i) => {
                return (
                  <div key={i} className="grid-item">
                    <AuctionCard item={item}></AuctionCard>
                  </div>
                );
              })}
            </Masonry>
          )}
        </div>
        <div className={style.ranking}>
          <RankLike></RankLike>
        </div>
      </div>
      <div ref={ref}>.</div>
    </>
  );
}

export default LoggedInHome;