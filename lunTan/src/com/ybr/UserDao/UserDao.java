package com.ybr.UserDao;

import java.sql.Connection;
import java.util.List;

import com.ybr.entity.Reply;
import com.ybr.entity.Tiezi;
import com.ybr.entity.Type;
import com.ybr.entity.User;

public interface UserDao {

	/**
	 * 去数据库查找用户名
	 * @param username 用户输入的用户名通过service传过来的
	 * @return 返回查到的结果数值
	 */
	User findUserName(String username);

	/**
	 * 往数据库中添加用户操作
	 * @param user 页面传来的user对象
	 * @return 
	 */
	int addUser(User user);

	/**
	 * 查询数据库中是否包含登录操作中输入的用户信息
	 * @param conn 事务的连接对象
	 * @param user 页面封装打包的用户信息
	 * @return 返回一个设定值，用于判断
	 */
	User CheckUser(Connection conn, User user);
	
	/**
	 * 将登陆的时间添加到数据库中
	 * @param conn 事务的连接对象
	 * @param user 页面封装打包的用户信息
	 */
	void addTime(Connection conn, User user);

	/**
	 * 去数据库中查询类型
	 * @return 返回一个type类型的对象的list
	 */
	List<Type> CheckType();

	/**
	 * 获取所有的帖子
	 * @return 查询到所有帖子的列表
	 */
	List<Tiezi> checkTieziAll();

	/**
	 * 根据帖子id查询帖子
	 * @param tid 帖子的id
	 * @return 返回一个tiezi的列表
	 */
	List<Tiezi> checkTiezi(String tid);

	/**
	 * 根据帖子中的userid查询user的username
	 * @param userid 帖子中的userid
	 * @return 返回一个User对象
	 */
	User checkUserById(Integer userid);

	/**
	 * 根据帖子中的帖子id查询帖子的类型
	 * @param typeid 帖子的类型id
	 * @return 返回一个Type类型的对象
	 */
	Type checkTypeById(Integer typeid);

	/**
	 * 根据帖子id查询数据库帖子内容
	 * @param id 页面的帖子id
	 * @return tiezi类型的对象
	 */
	Tiezi findTieziCont(String id);

	/**
	 * 根据帖子id查找回复列表
	 * @param id 帖子id
	 * @return 回复列表
	 */
	List<Reply> findReplyList(String id);

	/**
	 * 根据打包传过来的reply对象来网数据库中添加回复内容
	 * @param conn 事务连接对象
	 * @param reply 封装好的reply对象
	 */
	void addReply(Connection conn, Reply reply);

	/**
	 * 根据帖子的id进行count的添加
	 * @param conn 事务连接对象
	 * @param tieziid 帖子的id
	 */
	void updateCount(Connection conn, Integer tieziid);

	/**
	 * 查询数据库中帖子列表的总长度
	 * @return 长度
	 */
	long checkTieziTotal();

	/**
	 * 当帖子类型id为空的时候根据页面条数查询所有的帖子列表
	 * @param offset 开始的位置
	 * @param size 页面的条数
	 * @return 返回一个list
	 */
	List<Tiezi> checkTieziByPage(int offset, int size);

	/**
	 * 根据帖子的类型id去数据库查询当前类型的帖子的总数
	 * @param tid 帖子类型的id
	 * @return 帖子的总数count
	 */
	long checkTieziTotalByTypeid(String tid);

	/**
	 * 根据帖子类型id查询帖子且根据页面条数查询所有的帖子列表
	 * @param tid 帖子类型id
	 * @param offset 开始的位置
	 * @param size 页面的条数
	 * @return 返回一个list
	 */
	List<Tiezi> checkTieziByPtid(String tid, int offset, int size);

	/**
	 * 根据封装的用户名和密码查询用户信息
	 * @param user 封装的用户
	 * @return 用户信息的user对象
	 */
	User findUser(User user);

	
	/**
	 * 根据用户id查询帖子的数量
	 * @param userid 用户id
	 * @return long类型的数值
	 */
	long checkTieziTotalByUid(Integer userid);

	/**
	 * 根据用户id查询该用户的帖子列表，并且根据页面条数分页
	 * @param userid 用户id
	 * @param offset 开始的页面
	 * @param size 页面信息条数
	 * @return 一个tiezi类型的list
	 */
	List<Tiezi> findTieziByUidPageNum(Integer userid, int offset, int size);

	/**
	 * 根据传过来的帖子对象添加帖子
	 * @param tiezi 帖子对象
	 * @return 一个数值
	 */
	int addText(Tiezi tiezi);

	
	/**
	 * 根据帖子的id删除该帖子下的所有回复内容
	 * @param conn 事务连接对象
	 * @param id 帖子的id
	 */
	void delReplyByid(Connection conn, String id);

	/**
	 * 根据当前用户对象删除当前id的帖子
	 * @param conn 事务的连接对象
	 * @param id 帖子的id
	 * @param user 用户对象
	 */
	void delTieziById(Connection conn, String id);

	/**
	 * 根据用户的id去修改用户的性别
	 * @param userid 用户id
	 * @param sex 用户修改的性别
	 */
	void updateSexByUid(Integer userid,String sex);

	/**
	 * 根据封装了的用户去修改密码
	 * @param newpwd 新密码
	 * @param user 封装的用户
	 * @return 一个数值
	 */
	int updatePwd(String newpwd, User user);

	/**
	 * 将当前的图片名保存到数据库中
	 * @param user 当前的用户对象
	 */
	void uploadImg(User user);



}
