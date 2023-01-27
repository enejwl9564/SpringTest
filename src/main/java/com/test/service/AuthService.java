package com.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.MemberDao;
import com.test.dto.AuthDto;
import com.test.dto.LoginDto;
import com.test.dto.MemberDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AuthService {
	
	public static String code;
	BCrypt bc = new BCrypt();
 
	
	@Autowired
	MemberDao dao;

	//Login확인
	public boolean LoginCheck(LoginDto dto, HttpServletRequest request) {
		boolean flag=false;
		
		
		String email = dto.getEmail();
		String pwd = dto.getPwd();
		
		MemberDto mdto= dao.Select(email);
		if(mdto!=null) //ID 일치 여부확인 OK(DB)
		{
			if( bc.checkpw(pwd, mdto.getPwd()) ) //PW 일치 여부확인(DB)
			{
				//ID/PW일치한다면 email/grade(권한) 을 Session에 저장
				AuthDto adto = new AuthDto();
				adto.setEmail(email);
				adto.setGrade(mdto.getGrade());
				
				//Session 유지시간 설정
				HttpSession session = request.getSession();
				session.setAttribute("authdto", adto);
				session.setMaxInactiveInterval(60*5);

				//true 전달
				flag=true;
			}
			
		}
		return flag;
	}
	
	
	public boolean LogoutRequest(HttpServletRequest req) {
		boolean flag=false;

		HttpSession session = req.getSession(false);
		if(session!=null) {
			session.invalidate();
			flag=true;
		}
			
		
		return flag;
	}
	
	
	
//	public boolean KakaoLoginCheck(Map<String, String[]> params, HttpServletRequest req) {
//		boolean flag=false;
//		String email = params.get("email")[0];
//		String gender = params.get("gender")[0];
//		String profile_image = params.get("profile_image")[0];
//		
//
//		//ID/PW일치한다면 email/grade(권한) 을 Session에 저장
//		AuthDto adto = new AuthDto();
//		adto.setEmail(email);
//		adto.setGrade("1");
//				
//		//Session 유지시간 설정
//		HttpSession session = req.getSession();
//		session.setAttribute("authdto", adto);
//		session.setMaxInactiveInterval(60*5);
//
//		//true 전달
//		flag=true;
//		
//		return flag;
//	
//	}
//	
//	
//	//임시메일번호 발송하기
//	public boolean EmailCodeSnd(HttpServletRequest req, HttpServletResponse resp) {
//		boolean flag = false;
//		
//		try {
//				String email = req.getParameter("email");
//		
//				Properties props = new Properties();
//				props.put("mail.smtp.host", "smtp.gmail.com");
//				props.put("mail.smtp.port", "587");
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.starttls.enable", "true");
//				props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		
//				Session session = Session.getInstance(props, new Authenticator() {
//					@Override
//					protected PasswordAuthentication getPasswordAuthentication() {
//						// 관리자 메일 , 구글 2단계인증 비밀번호
//						return new PasswordAuthentication("wjddnrbs0987@gmail.com", "fjjgakdscqhyiwja");
//					}
//				});
//		
//				String receiver = email; // 메일 받을 주소
//				String title = "[WEB-TEST]본인확인 인증코드";
//		
//				// 코드 발급(난수 6자리)
//				code = (int) (Math.random() * 1000000) + "";
//		
//				String content = code;
//				Message message = new MimeMessage(session);
//				
//				message.setFrom(new InternetAddress("wjddnrbs0987@gmail.com", "관리자", "utf-8"));
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
//				message.setSubject(title);
//				message.setContent(content, "text/html; charset=utf-8");
//		
//				Transport.send(message);
//				System.out.println("메일전송 완료!!!!!!!!!");
//				flag=true;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
//	
//	//임시 패스워드 보내기
//	public boolean TmpPwdSnd(HttpServletRequest req, HttpServletResponse resp) {
//		boolean flag = false;
//		
//		try {
//				String email = req.getParameter("email");
//				String recvcode = req.getParameter("code");
//				
//				System.out.println("받은 코드 : " + recvcode);
//				System.out.println("저장 코드 : " + code);
//				
//				
//				Properties props = new Properties();
//				props.put("mail.smtp.host", "smtp.gmail.com");
//				props.put("mail.smtp.port", "587");
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.starttls.enable", "true");
//				props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		
//				Session session = Session.getInstance(props, new Authenticator() {
//					@Override
//					protected PasswordAuthentication getPasswordAuthentication() {
//						// 관리자 메일 , 구글 2단계인증 비밀번호
//						return new PasswordAuthentication("wjddnrbs0987@gmail.com", "fjjgakdscqhyiwja");
//					}
//				});
//		
//				if (recvcode.equals(code)) 
//				{
//					String receiver = email; // 메일 받을 주소
//					String title = "[WEB-TEST]임시패스워드 발급안내";
//
//					// 코드 발급(난수 8자리)
//					String tmppwd = (int) (Math.random() * 100000000) + "";
//					System.out.println("임시패스워드 코드 : " + tmppwd);
//					String content = tmppwd;
//					Message message = new MimeMessage(session);
//					
//					message.setFrom(new InternetAddress("wjddnrbs0987@gmail.com", "관리자", "utf-8"));
//					message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
//					message.setSubject(title);
//					message.setContent(content, "text/html; charset=utf-8");
//
//					Transport.send(message);
//					
//					
//					// DB로 임시패스워드 만들어서 저장
//					String cpw =  bc.hashpw(tmppwd, bc.gensalt());
//					MemberDto mdto = new MemberDto();
//					mdto.setEmail(email);
//					mdto.setPwd(cpw);
//					dao.Update(mdto);
//				
//					flag=true;
//				}
//				
//		
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
//	
	
	
	
}

