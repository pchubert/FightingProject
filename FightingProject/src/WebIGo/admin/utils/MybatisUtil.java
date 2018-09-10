package WebIGo.admin.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by winter on 18-8-8.
 */
public class MybatisUtil {
    private static SqlSessionFactory sessionFactory;
    private static InputStream is;

    static {
        //mybatis的配置文件
		String resource = "MybatisConfig.xml";
		//使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		//InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
		is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
		//构建sqlSession的工厂
		sessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    public static SqlSessionFactory getInstance() {
        return sessionFactory;
    }
}