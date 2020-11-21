package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;

public class CreateCourseApplication {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try { 
			session.beginTransaction();
			 
			//get the instructor from the database
			Instructor instructor = session.get(Instructor.class, 5);
			
			//create some courses
			Course course1 = new Course("Angular", instructor);
			Course course2 = new Course("C++", instructor);
			Course course3 = new Course("Python", instructor);
			
			//add courses to the instructor
			instructor.add(course1);
			instructor.add(course2);
			instructor.add(course3);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
			factory.close();
		}
	}
}
