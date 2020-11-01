package com.ybr.UserService.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserDao.UserDao;
import com.ybr.UserDao.Impl.UserDaoImpl;
import com.ybr.UserService.UserService;
import com.ybr.entity.Page;
import com.ybr.entity.Reply;
import com.ybr.entity.Tiezi;
import com.ybr.entity.Type;
import com.ybr.entity.User;
import com.ybr.utils.DBUtil;

public class UserServiceImpl implements UserService {

	private UserDao ud = new UserDaoImpl();
	
	
	@Override
	public User checkUsername(String username) {
		User user = ud.findUserName(username);		
		return user;
	}


	@Override
	public int register(User user) {
		int jg=ud.addUser(user);
		return jg;
	}


	@Override
	public User login(User user) {
		Connection conn =null;
		User jguser =null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			jguser = ud.CheckUser(conn,user);
			ud.addTime(conn,user);
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if(conn!=null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("该用户名还未注册！");
			}
		}
		return jguser;
	}


	@Override
	public List<Type> checkType() {
		return ud.CheckType();
	}


	@Override
	public List<Tiezi> checkTiezi(String tid) {
		List<Tiezi> tieziList =null;
		//对tid进行判断
		if(StringUtils.isBlank(tid)) {
			tieziList=ud.checkTieziAll();
		}else{
			tieziList = ud.checkTiezi(tid);
		}
		
		//
		for (Tiezi tiezi : tieziList) {
			//根据帖子中的uid查询用户名
			User user = ud.checkUserById(tiezi.getUserid());
			//将查询到的用户名放到Tiezi的user对象中去
			tiezi.setUser(user);
			Type type = ud.checkTypeById(tiezi.getTypeid());
			tiezi.setType(type);
		}
		return tieziList;
	}


	@Override
	public Tiezi findTieziCont(String id) {
		//根据帖子id获取帖子
		Tiezi tz = ud.findTieziCont(id);
		if(tz!=null) {
			//根据帖子id获取用户信息
			User user = ud.checkUserById(tz.getUserid());
			//根据帖子id获取帖子类型
			Type type = ud.checkTypeById(tz.getTypeid());
			//根据帖子id获取回复列表
			List<Reply> list = ud.findReplyList(id);
			
			//将回复列表循环出来，并且获取其中user对象信息
			for (Reply reply : list) {
				User replyUser = ud.checkUserById(reply.getUserid());
				reply.setUser(replyUser);
			}
			
			tz.setUser(user);
			tz.setType(type);
			tz.setList(list);
		}
		
		return tz;
	}


	@Override
	public void addReply(Reply reply) {
		Connection conn =null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			ud.addReply(conn,reply);
			ud.updateCount(conn,reply.getTieziid());
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
	}


	@Override
	public Page checkTieziByPage(Integer pageNum, String tid) {
		//当页面的tid为空的时候那么就是根据发表时间且满足列表条数来加载所有内容那个的帖子
		Page page = new Page();
		page.setPageNum(pageNum);
		//设置页面列表条数
		page.setSize(5);
		//页面
		int size = page.getSize();
		int offset = (pageNum-1)*size;
		
		if(StringUtils.isBlank(tid)) {
			//列表的总数
			long totals=ud.checkTieziTotal();
			page.setTotal((int)totals);
			int total = page.getTotal();
			
			//总页码
			int end = ((total%size)>0?(total/size+1):(total/size));
			page.setEnd(end);
			//根据页面条数去数据库中查询帖子列表信息
			List<Tiezi> list = ud.checkTieziByPage(offset,size);
			for (Tiezi tiezi : list) {
				//根据查询到的帖子的用户id查询用户名
				User user = ud.checkUserById(tiezi.getUserid());
				tiezi.setUser(user);
				//根据查询到的帖子的类型id查询帖子的类型
				Type type = ud.checkTypeById(tiezi.getTypeid());
				tiezi.setType(type);
			}
			page.setList(list);
		}else {
			//如果tid不为空则根据帖子的类型id查询帖子列表并按照页面条数进行加载
			//根据帖子类型的id查询帖子列表总数
			long totals=ud.checkTieziTotalByTypeid(tid);
			page.setTotal((int)totals);
			int total = page.getTotal();
			
			//页面的总页码
			int end = ((total%size)>0?(total/size+1):(total/size));
			page.setEnd(end);
			//根据页面条数和帖子类型id去数据库中查询帖子列表信息
			List<Tiezi> list = ud.checkTieziByPtid(tid,offset,size);
			for (Tiezi tiezi : list) {
				//根据查询到的帖子的用户id查询用户名
				User user = ud.checkUserById(tiezi.getUserid());
				tiezi.setUser(user);
				//根据查询到的帖子的类型id查询帖子的类型
				Type type = ud.checkTypeById(tiezi.getTypeid());
				tiezi.setType(type);
			}
			page.setList(list);
		}
		
		return page;
	}


	@Override
	public User checkUser(User user) {
		return ud.findUser(user);
	}


	@Override
	public Page checkTieziByUid(Integer userid, Integer pageNum) {
		Page pageList = new Page();
		pageList.setSize(3);
		pageList.setPageNum(pageNum);
		int size = pageList.getSize();
		int offset = (pageNum-1)*size;
		long totals=ud.checkTieziTotalByUid(userid);
		pageList.setTotal((int)totals);
		int total = pageList.getTotal();
		int end = ((total%size)>0?(total/size+1):(total/size));
		pageList.setEnd(end);
		List<Tiezi> list = ud.findTieziByUidPageNum(userid,offset,size);
		for (Tiezi tiezi : list) {
			//根据查询到的帖子的类型id查询帖子的类型
			Type type = ud.checkTypeById(tiezi.getTypeid());
			tiezi.setType(type);
		}
		pageList.setList(list);
		
		return pageList;
	}


	@Override
	public int addText(Tiezi tiezi) {
		
		return ud.addText(tiezi);
	}


	@Override
	public void delTextById(String id) {
		//因为回复表中包含帖子表的id作为外键，且不为空，所以删除帖子的同时也要删除当前帖子的回复内容
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			//根据帖子id删除回复内容
			ud.delReplyByid(conn,id);
			//根据帖子id删除帖子
			ud.delTieziById(conn,id);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if(null!=conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


	@Override
	public User findUserById(String id) {
		//由于UserDao中有一个findUserById的方法，但是传的id是integer类型
		//把id转为integer类型
		Integer userid = Integer.parseInt(id);
		return ud.checkUserById(userid);
	}


	@Override
	public void updateSex(Integer userid, String sex) {
		ud.updateSexByUid(userid,sex);
	}


	@Override
	public int updatePwd(String newpwd, User user) {
		return ud.updatePwd(newpwd,user);
	}


	@Override
	public void uploadImg(User user) {
		ud.uploadImg(user);
	}
	
	
	
	
	
	
	
	
	
	
}
