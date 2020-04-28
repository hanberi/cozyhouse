package com.kh.portfolio.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.dao.RboardDAOImplXML;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

@Service
public class RboardSVCImpl implements RboardSVC {

	final private static Logger logger
	= LoggerFactory.getLogger(RboardDAOImplXML.class);
	
	@Inject
	RboardDAO rboardDAO;
	
	//댓글작성
	@Override
	public int write(RboardVO rboardVO) {
		return rboardDAO.write(rboardVO);
	}
	//댓글목록
	@Override
	public List<RboardVO> list(int bnum, int startRec, int endRec) {
		return rboardDAO.list(bnum, startRec, endRec);
	}
	//댓글수정
	@Override
	public int modify(RboardVO rboardVO) {
		return rboardDAO.modify(rboardVO);
	}	
	//댓글삭제
	@Override
	public int delete(String rnum) {
		return rboardDAO.delete(rnum);
	}
	
	//대댓글작성
	@Override
	public int reply(RboardVO rboardVO) {
		return rboardDAO.reply(rboardVO);
	}
	//댓글 호감,비호감
	//투표여부체크
	@Override
	public int checkVote(VoteVO voteVO) {
		int cnt = 0;
		//투표이력 있으면
		if(rboardDAO.checkVote(voteVO) ==1 ) {
			cnt = rboardDAO.updateVote(voteVO);
		}else {
			//투표이력 없으면
			cnt = rboardDAO.insertVote(voteVO);
		}
		return cnt;
	}

	@Override
	public int mergeVote(VoteVO voteVO) {
		logger.info("voteVO" + voteVO.toString());
		return rboardDAO.mergeVote(voteVO);
	}
	//댓글 총계
	@Override
	public int replyTotalRec(String bnum) {
		return rboardDAO.replyTotalRec(bnum);
	}

}
