package com.ybr.UserService;

import java.util.List;

import com.ybr.entity.Page;
import com.ybr.entity.Reply;
import com.ybr.entity.Tiezi;
import com.ybr.entity.Type;
import com.ybr.entity.User;

public interface UserService {
	/**
	 * 验证用户名是否重复
	 * @param username 用户在输入框中输入的用户名
	 * @return 查询结果
	 */
	User checkUsername(String username);
	
	/**
	 * 用户注册功能
	 * @param user 用户在页面填写的信息，封装后的对象
	 * @return 返回一个数值，如果为1则注册成功，如果为0则注册失败
	 */
	int register(User user);

	/**
	 * 用户登录
	 * @param user 用户页面信息封装成user对象
	 * @return 返回一个服务器设定值，通过值判定是否登录成功
	 */
	User login(User user);
	
	
	/**
	 * 取论坛类型数据
	 * @return 返回一个type对象的list
	 */
	List<Type> checkType();
	
	/**
	 * 根据帖子的id查询帖子的列表
	 * @param tid 帖子的id
	 * @return 返回一个Tiezi对象的list
	 */
	List<Tiezi> checkTiezi(String tid);
	
	
	/**
	 * 根据帖子id查找帖子内容
	 * @param id 页面传的帖子的id
	 * @return 帖子类型的对象
	 */
	Tiezi findTieziCont(String id);

	/**
	 * 添加用户回复内容
	 * @param reply 封装好的reply对象
	 */
	void addReply(Reply reply);

	/**
	 * 根据页面条数和帖子类型来查询帖子列表
	 * @param pageNum 页面帖子条数
	 * @param tid 帖子类型id
	 * @return 一个page对象
	 */
	Page checkTieziByPage(Integer pageNum, String tid);

	/**
	 * 根据登录的用户来查询用户信息
	 * @param user 封装的user对象
	 * @return 用户所有信息的user对象
	 */
	User checkUser(User user);

	/**
	 * 根据用户的id查询当前用户的帖子列表并根据pageNum进行分页
	 * @param userid 当前用户的id
	 * @param pageNum 当前页面的值
	 * @return 一个page对象
	 */
	Page checkTieziByUid(Integer userid, Integer pageNum);

	/**
	 * 将帖子对象发送到服务器
	 * @param tiezi 封装好的帖子对象
	 * @return 一个数值用户判断是否成功
	 */
	int addText(Tiezi tiezi);

	/**
	 * 根据当前用户的信息来删除对应的帖子id的帖子
	 * @param id 帖子的id
	 */
	void delTextById(String id);

	/**
	 * 同过user的id去查用户的所有信息
	 * @param id 用户的id
	 * @return 查询到的user对象
	 */
	User findUserById(String id);

	/**
	 * 根据userid更改用户性别
	 * @param userid 用户id
	 * @param sex 用户性别
	 */
	void updateSex(Integer userid, String sex);

	/**
	 * 根据封装了的用户更改密码
	 * @param newpwd 新密码
	 * @param user 封装的用户
	 * @return 一个数值用于判断是否跟新成功
	 */
	int updatePwd(String newpwd, User user);

	/**
	 * 将当前的图片名保存到数据库中
	 * @param user 当前的用户对象
	 */
	void uploadImg(User user);



}
