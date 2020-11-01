package com.ybr.UserDao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ybr.UserDao.UserDao;
import com.ybr.entity.Reply;
import com.ybr.entity.Tiezi;
import com.ybr.entity.Type;
import com.ybr.entity.User;
import com.ybr.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

	@Override
	public User findUserName(String username) {
		
		try {
			return qr.query("select username from user where username=?", new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败！");
		}
	}

	@Override
	public  int addUser(User user) {
		int jg;
		try {
			qr.update("insert into user (username,password,regtime)values(?,?,?)",user.getUsername(),user.getPassword(),user.getRegtime());
		} catch (SQLException e) {
			e.printStackTrace();
			return jg=1;
		}
		return jg=0;
	}

	@Override
	public User CheckUser(Connection conn, User user) {
		QueryRunner qr = new QueryRunner();
		User jguser = null;
		try {
			jguser = qr.query(conn, "select * from user where username=? and password=?",new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败！");
		}
		return jguser;
	}

	@Override
	public void addTime(Connection conn, User user) {
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn, "update user set logintime=? where username=? and password=?",user.getLogintime(),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加登录时间失败！");
		}
	}

	@Override
	public List<Type> CheckType() {
		try {
			return qr.query("select * from type", new BeanListHandler<Type>(Type.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询类型列表失败！");
		}
	}

	@Override
	public List<Tiezi> checkTieziAll() {
		try {
			return qr.query("select * from tiezi", new BeanListHandler<Tiezi>(Tiezi.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有帖子失败！");
		}
	}

	@Override
	public List<Tiezi> checkTiezi(String tid) {
		try {
			return qr.query("select * from tiezi where typeid=?", new BeanListHandler<Tiezi>(Tiezi.class),tid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询帖子类型失败！");
		}
	}

	@Override
	public User checkUserById(Integer userid) {
		try {
			return qr.query("select * from user where id=?", new BeanHandler<User>(User.class),userid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败！");
		}
	}

	@Override
	public Type checkTypeById(Integer typeid) {
		try {
			return qr.query("select * from type where id=?", new BeanHandler<Type>(Type.class),typeid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询帖子类型失败！");
		}
	}

	@Override
	public Tiezi findTieziCont(String id) {
		try {
			return qr.query("select * from tiezi where id=?", new BeanHandler<Tiezi>(Tiezi.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据id查询帖子内容失败！");
		}
	}

	@Override
	public List<Reply> findReplyList(String id) {
		try {
			return qr.query("select * from reply where tieziid=?", new BeanListHandler<Reply>(Reply.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据id查询回复列表失败！");
		}
	}

	@Override
	public void addReply(Connection conn, Reply reply) {
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn, "insert into reply (replycontent,replytime,tieziid,userid) values(?,?,?,?)",reply.getReplycontent(),reply.getReplytime(),reply.getTieziid(),reply.getUserid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加回复内容失败！");
		}
	}

	@Override
	public void updateCount(Connection conn, Integer tieziid) {
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn,"update tiezi set count=count+1 where id=?;",tieziid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新回复数量失败！");
		}
	}

	@Override
	public long checkTieziTotal() {
		try {
			return qr.query("select count(*) from tiezi", new ScalarHandler<>());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询帖子长度失败！");
		}
	}

	@Override
	public List<Tiezi> checkTieziByPage(int offset, int size) {
		try {
			return qr.query("select * from tiezi order by fabutime desc limit ?,?", new BeanListHandler<Tiezi>(Tiezi.class),offset,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有帖子失败！");
		}
	}

	@Override
	public long checkTieziTotalByTypeid(String tid) {
		try {
			return qr.query("select count(*) from tiezi where typeid=?", new ScalarHandler<>(),tid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据帖子类型id查询帖子长度失败！");
		}
	}

	@Override
	public List<Tiezi> checkTieziByPtid(String tid, int offset, int size) {
		try {
			return qr.query("select * from tiezi where typeid=? order by fabutime desc limit ?,?", new BeanListHandler<Tiezi>(Tiezi.class),tid,offset,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据帖子类型id查询帖子列表失败！");
		}
	}

	@Override
	public User findUser(User user) {
		try {
			return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户信息失败！");
		}
	}

	@Override
	public long checkTieziTotalByUid(Integer userid) {
		try {
			return qr.query("select count(*) from tiezi where userid=?", new ScalarHandler<>(),userid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户id查询信息数量失败！");
		}
	}

	@Override
	public List<Tiezi> findTieziByUidPageNum(Integer userid, int offset, int size) {
		try {
			return qr.query("select * from tiezi where userid=? order by fabutime desc limit ?,?", new BeanListHandler<Tiezi>(Tiezi.class),userid,offset,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户id查询分页失败！");
		}
	}

	@Override
	public int addText(Tiezi tiezi) {
		int jg;
		try {
			qr.update("insert into tiezi (title,content,fabutime,userid,count,typeid) values(?,?,?,?,?,?)",tiezi.getTitle(),tiezi.getContent(),tiezi.getFabutime(),tiezi.getUserid(),tiezi.getCount(),tiezi.getTypeid());
		} catch (SQLException e) {
			e.printStackTrace();
			return jg=0;
		}
		return jg=1;
	}

	
	@Override
	public void delReplyByid(Connection conn, String id) {
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn,"delete from reply where tieziid=?",id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据当前帖子id删除当前帖子回复内容失败！");
			
		}
		
	}

	@Override
	public void delTieziById(Connection conn, String id) {
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn,"delete from tiezi where id=?",id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据当前帖子id的删除帖子失败！");
			
		}
	}

	@Override
	public void updateSexByUid(Integer userid,String sex) {
		try {
			qr.update("update user set sex=? where id=?", sex,userid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更改用户性别失败！");
		}
	}

	@Override
	public int updatePwd(String newpwd, User user) {
		int jg;
		//先进行用户信息的判断
		try {
			User jguser = qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
			if(jguser!=null) {
				qr.update("update user set password=? where username=? and password=?",newpwd,user.getUsername(),user.getPassword());
				return jg=1;
			}else {
				return jg=0;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更改用户密码失败！");
		}
	}

	@Override
	public void uploadImg(User user) {
		try {
			qr.update("update user set faceimage=? where id=?",user.getFaceimage(),user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("头像上传失败！");
		}
	}




}
