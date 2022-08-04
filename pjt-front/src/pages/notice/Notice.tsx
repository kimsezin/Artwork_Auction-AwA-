import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import api from "../../api/api";
import NoticeListItem from "../../component/NoticeListItem";
import { loadingActions } from "../../store";
import { NoticeItem } from "./../../Interface";
import style from "./Notice.module.css";

function Notice(): JSX.Element {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [noticeList, setNoticeList] = useState<NoticeItem[]>([]);

  const onClick = () => {
    navigate("/notice/create");
  };

  useEffect(() => {
    const callNotice = async () => {
      dispatch(loadingActions.toggle());
      try {
        const response = await api.notice.readAll();
        dispatch(loadingActions.toggle());
        if (response.status === 200) {
          const notices = response.data;
          const newNotices: NoticeItem[] = notices.map((notice: NoticeItem) => {
            const { notice_id, title, content, createdDate, modifiedDate } =
              notice;
            const newNotice: NoticeItem = {
              notice_id,
              title,
              content,
              createdDate,
              modifiedDate,
            };
            return newNotice;
          });
          setNoticeList(newNotices);
        } else {
          alert("정보 조회에 실패했습니다.");
          navigate("/");
        }
      } catch (err) {
        dispatch(loadingActions.toggle());
        console.error(err);
      }
    };

    callNotice();
  }, [navigate, dispatch]);

  return (
    <div className={style.notice}>
      <section className={style.noticeTop}>
        <div>
          <div className={style.title}>공지사항</div>
          <div className={style.content}>
            Artwork Auction의 공지사항을 확인해보세요!
          </div>
        </div>
      </section>
      <ul>
        <div className={style.ulTop}>
          <div>제목</div>
          <div>작성일</div>
        </div>
        {noticeList.map((notice) => {
          return (
            <li key={notice.notice_id}>
              <NoticeListItem notice={notice}></NoticeListItem>
            </li>
          );
        })}
      </ul>
      <div className={style.buttonBox}>
        <button onClick={onClick} className={style.btn}>
          작성
        </button>
      </div>
    </div>
  );
}

export default Notice;