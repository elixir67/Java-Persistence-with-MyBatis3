package chapter04;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter04.domain.Student;
import chapter04.domain.Tutor;


public class StudentService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<Student> findAllStudents()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper StudentMapper = sqlSession.getMapper(StudentMapper.class);
			logger.debug("StudentMapper-DEBUG :"+StudentMapper);
			return StudentMapper.findAllStudents();
		} finally {
			sqlSession.close();
		}
	}
	
	public Student findStudentById(Integer id)
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			logger.debug("StudentMapper-DEBUG :"+studentMapper);
			return studentMapper.findStudentById(id);
		} finally {
			sqlSession.close();
		}
	}

	public Student findStudentWithAddressById(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.selectStudentWithAddress(id);
		} finally {
			sqlSession.close();
		}
	}
	
	public Student createStudent(Student student) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			mapper.insertStudent(student);
			sqlSession.commit();
			return student;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
	
	public void createStudentWithMap(Map<String, Object> studentDataMap) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			System.err.println("with map");
			mapper.insertStudentWithMap(studentDataMap);
			sqlSession.commit();
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}

	public Student updateStudent(Student student) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			mapper.updateStudent(student);
			sqlSession.commit();
			return student;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
	
	public boolean deleteStudent(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			int count = mapper.deleteStudent(id);
			sqlSession.commit();
			return count > 0;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
	
	public Tutor findTutorById(int tutorId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			return mapper.selectTutorById(tutorId);
		} 
		
		finally {
			sqlSession.close();
		}
	}
}
