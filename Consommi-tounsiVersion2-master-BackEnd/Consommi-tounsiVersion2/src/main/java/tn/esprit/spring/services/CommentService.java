package tn.esprit.spring.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Dictionary;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.SmsRequest;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.DictionaryRepository;
import tn.esprit.spring.repository.IClientRepository;

@Service
public class CommentService implements ICommentService{
	@Autowired
	CommentRepository commentrep;
	
	@Autowired
	IClientRepository clientrep;
	
	@Autowired
	ClientServicesImpl clientser;
	
	@Autowired
	DictionaryRepository dictionaryrep;
	
	@Autowired
	DictionaryService dictionaryser;
	
	@Autowired 
	SmsService smsService;
	
	
	
	
	
	@Override
	public int addComment(Comment comment) {
		
		commentrep.save(comment);
		return comment.getId_comment();
	}

	@Override
	public List<Comment> commentLists() {
		List<Comment> commentLists =(List<Comment>) commentrep.findAll();
		return commentLists;}
		
	//@Override
//	public List<Comment> CommentLists(int id_subject) {
//		//if(Comment.getSubjectforum().getId_subject()==id_subject){
//		//List<Comment> CommentLists=(List<Comment>) commentrep.findAll();
//		//return CommentLists;}
//		//else 
//		return null;
	//}

	@Override
	public Comment updateComment (Comment comment) {
		return commentrep.save(comment);
	}

	@Override
	public void deleteComment(int id) {
		commentrep.deleteById(id);
		
	}
	@Override
	public void filtreBadWords(int id_comment) {
		Comment comment=commentrep.findById(id_comment).orElse(null);
		//Dictionary dictionary=dictionaryrep.findById(id_mot).orElse(null);
			String[] WordsOfComment=comment.getText_comment().split(" ");
			for(String word:WordsOfComment){
				String word1=word.toUpperCase();

				}
			List<Dictionary> listbw = dictionaryser.DictionaryLists();
			List<String> badWords=new ArrayList<String>();
			
			for(Dictionary d:listbw){
				badWords.add(d.getMot());
			}
			for(String word1:WordsOfComment){
				for(String badword:badWords){
					if(word1.compareTo(badword)==0){
						int idC=comment.getClient().getId_user();
						commentrep.delete(comment);
						if(comment.getClient().getAlerte()==0){
							clientser.updateClient(idC);
							
							String msg="Hello,Dear Mr/Mrs "+"  "+comment.getClient().getFirst_name()+" "+comment.getClient().getLast_name()
									+"  you wrote a bad comment that was already deleted. please respect the rules of our site if not you will be blocked"+
											" --- CONSOMMI TOUNSI---- ";
									SmsRequest smsRequest = new SmsRequest ("+21656628701",msg);
									//send a mail sms to the client 
									smsService.sendSms(smsRequest );
							
						}
						else if (comment.getClient().getAlerte()==1){
							int id=comment.getClient().getId_user();
							//bloquer par angular
							//clientrep.deleteById(id);
							String msg="Hello,Dear Mr/Mrs "+"  "+comment.getClient().getFirst_name()+" "+comment.getClient().getLast_name()
									+" you wrote a bad comment for the second time. unfortunately you are blocked"+
											" --- CONSOMMI TOUNSI---- ";
									SmsRequest smsRequest = new SmsRequest ("+21656628701",msg);
									smsService.sendSms(smsRequest );
						}
						
						
//						SmsRequest smsRequest = new SmsRequest ("+21656628701","bnj lamis");
//						//send a mail sms to the client 
//						smsService.sendSms(smsRequest );
					}
				}
			}
			
			
	}

	
}