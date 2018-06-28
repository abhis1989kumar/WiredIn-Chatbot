import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class SpeakingChatbot extends JFrame {

	JPanel panel;
	JTextField User_Input;
	public static JTextArea ChatHistory;
	JButton Enter;
	Map<String, String> employees;
	public static  int counter=0;
	public static  int greetingCounter=0;
	public static  int sorryCounter=0;

	
	boolean closeChatbot=false;

	String name="unknown";

	public SpeakingChatbot() {

		employees=new HashMap<String, String>();

		panel = new JPanel();
		User_Input = new JTextField();
		ChatHistory = new JTextArea();
		//Enter = new JButton("Enter");
		this.setSize(2000, 1100);
		this.setVisible(true);
		this.add(new JLabel(new ImageIcon("Cyr3con.JPG")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		this.add(panel);
		ChatHistory.setBounds(640, 330, 1200, 540);
		ChatHistory.setFont(new Font("Consolas",Font.PLAIN,25));
		ChatHistory.setForeground(new Color(42,251,8));//(new Color(42,251,8));
		ChatHistory.setBackground(Color.black);

	//	ChatHistory.setBackground(Color.black);


		panel.add(ChatHistory);
		panel.setFont(new Font("Consolas",Font.BOLD,30));
		panel.setForeground(Color.white);


		User_Input.setBounds(640, 900, 1200, 50);
		User_Input.setFont(new Font("Consolas",Font.PLAIN,25));

		panel.add(User_Input);
	//	Enter.setBounds(375, 400, 95, 30);
		//panel.add(Enter);
		this.setTitle("  WiredIn");
		
		//Adding cyr3con image icon
		//JImageComponent ic = new JImageComponent("Cyr3con.JPG");
		JLabel lblPic = new JLabel();
		lblPic.setIcon(new ImageIcon("E:\\Chatbots\\Chatbot1\\src\\Cyr3con.JPG"));
		lblPic.setBounds(30, 0, 420, 420);
		panel.add(lblPic);
		
		//Adding robo image
			//	JImageComponent about = new JImageComponent("About.JPG");
				JLabel lblRobo = new JLabel();
				lblRobo.setIcon(new ImageIcon("E:\\Chatbots\\Chatbot1\\src\\RoboAnim.gif"));
				lblRobo.setBounds(30, 600, 180, 250);
				panel.add(lblRobo);
				
				//Adding About image
					JLabel lblAbout = new JLabel();
					lblAbout.setIcon(new ImageIcon("E:\\Chatbots\\Chatbot1\\src\\About.gif"));
					lblAbout.setBounds(1300, 10, 556, 293);
					panel.add(lblAbout);
		//Adding title
		JLabel title = new JLabel("Interact with WiredIN....");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Verdana", Font.BOLD, 40));
		title.setBounds(640, 16, 800, 50);
		panel.add(title);
		
		JLabel subTitle = new JLabel("Know more about CYR3CON !!!");
		subTitle.setForeground(Color.GREEN);
		//subTitle.setBackground(Color.BLACK);
		subTitle.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 30));
		subTitle.setBounds(640, 75, 800, 34);
		panel.add(subTitle);
		

		JOptionPane jOptionPane=new JOptionPane();
		jOptionPane.setSize(400, 400);

		//adding employees name and email address
		addEmployee("Abhishek Kumar", "To catch up with Abhishek Kumar,you can reach out to him at akuma168@asu.edu");
		//addEmployee("Jana Shakarian", "You can connect with her at jshak@asu.edu");
		//---
		try
		{
			Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\HeyImWiredIn.vbs"));
		}
		catch(IOException e)
		{
			System.out.println("Sorry...I am not able to get you\n");
		}
		

		ChatHistory.setText("Chat Messages>>>>> \n ");
		ChatHistory.setText(ChatHistory.getText()+" WiredIn  :  " + " Hey I am WiredIn...What's your name?" );

		

		User_Input.addKeyListener(new KeyListener() {


			public void keyTyped(KeyEvent ke) {



				if(ke.getKeyChar() == KeyEvent.VK_ENTER){

					if(!closeChatbot) { //if the chatbot session has not ended
						
						if (countLines(ChatHistory.getText())>=15){	
							ChatHistory.setText("");
						}
						if(counter ==0)   //first get the name of the user
						{
							try
							{
								Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\NiceToMeetYou.vbs"));
								name	= User_Input.getText();
								ChatHistory.setText(ChatHistory.getText() + "\n"+ name +" : "  + User_Input.getText());

								ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Nice to meet you.." + name +".How can I help you today?");
								ChatHistory.setText(ChatHistory.getText() + "\n"+ "             (Type Help for seeing the useful commands)");								

								counter++;
							}
							catch(IOException e)
							{
								e.printStackTrace();
							}

						}
						else {//counter >0

							ChatHistory.setText(ChatHistory.getText() + "\n"+ name +" : "  + User_Input.getText());
							String str= User_Input.getText();
							String str1=str.substring(1, str.length());
							System.out.println("^^"+str1.toLowerCase());
							System.out.println("^^##"+employees);

							System.out.println("chk**"+str1.toLowerCase().matches("clear".toLowerCase()));
						 if(employees.containsKey(str1.toLowerCase()))
							{
								chatbotOutput("ConnectonthisEmailID", employees.get(str1.toLowerCase()));
							}
						 else if (str1.toLowerCase().matches("HELP".toLowerCase()))
						 {
							
							chatbotOutput("HelpSpeak", "Please Refer the commands displayed on the Help Screen..");
							try {
								Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\Help.bat"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							 
						 }
						 else if (str1.toLowerCase().matches("FAQ".toLowerCase()))
						 {
								/*System.out.println("inside FAQ");

							 FileReader file;
							 try {
								 file = new FileReader("E:\\Chatbots\\Chatbot1\\src\\FAQ.txt");
								 System.out.println("inside FAQ@@");

								 BufferedReader reader = new BufferedReader(file);
								 System.out.println("inside FAQ##");

								 String text="";
								 String line;
								 line = reader.readLine();
								 while(line!=null)
								 {
									 text+=line+"\n";
									 line= reader.readLine();
								 }
								 System.out.println("text##"+text);
								 
								 ChatHistory.setText(ChatHistory.getText()+"\n"+ adjustMessage2(text));
								 System.out.println("text##@");

								 reader.close();
								 System.out.println("text####@@");

							 } catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) { 
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 */
							 /*ChatHistory.setText(ChatHistory.getText()+"\n"+ "  WiredIn " +" : "  +  "Please Refer Frequently Asked Questions now..");
								try {
									Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\FAQ.bat"));
								} catch (IOException e) {
									e.printStackTrace();
								}
*/
							chatbotOutput("FAQspeak", "Please Refer Frequently Asked Questions displayed on the screen..");
							try {
								Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\FAQ.bat"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							 
						 }
							else if(str1.toLowerCase().matches("clear".toLowerCase()) || (str.toLowerCase().matches("clear".toLowerCase())))
							{
								ChatHistory.setText("Chat Messages>>>>> \n ");
							}
							else if(str1.toLowerCase().matches("(.*)OK(.*)".toLowerCase()) || (str1.toLowerCase().matches("there(.*)".toLowerCase())))
							{
								chatbotOutput("nextQuestn", "Yes....Please ask the next question if you wish !!!");
							}
							else if(str1.toLowerCase().matches("restart".toLowerCase())  ||  str1.toLowerCase().matches("reset".toLowerCase()))
							{
								counter=0;
								try
								{
									Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\HeyImWiredIn.vbs"));
								}
								catch(IOException e)
								{
									System.out.println("Sorry...I am not able to get you\n");
								}
								
								ChatHistory.setText("Chat Messages>>>>> \n ");
								ChatHistory.setText(ChatHistory.getText()+" WiredIn  :  " + " Hey I am WiredIn...What's your name?" );
							/*	ChatHistory.setText("Hey I am WiredIn...What's your name?");
								ChatHistory.setText("     WiredIn  :  " + ChatHistory.getText() );*/
							}
							else if(str.toLowerCase().matches("(.*)employee(.*)".toLowerCase()))
							{
								chatbotOutput("employeelist", "I have the employee contact list.Go ahead and type the name you are looking to connect..");

							}
							
							else if(str.toLowerCase().matches("(.*)language(.*)".toLowerCase()))
							{

								chatbotOutput("language",adjustMessage("We currently cover sixteen languages including English, Russian, Mandarin, Vietnamese, Bahasa, Arabic, Farsi, Turkish, Romanian, Spanish, French, Italian, Swedish, German, Portuguese, Japanese and are expanding."));   

							}    
							else if(str.toLowerCase().matches("(.*)network(.*)".toLowerCase()))
							{
								chatbotOutput("networks", "Networks used are Surface web (Clearnet), Tor, Freenet, i2p.");
							}  

							else if(str.toLowerCase().matches("(.*)darkweb(.*)".toLowerCase()))
							{
								chatbotOutput("darkwebs", adjustMessage("Dark web consists of Sites hosted on cryptonetworks which are accessible only through additional protocols. Not all so-called darkweb-content is bad, in fact most 'darknetizens' are privacy conscious geeks."));     
							} 
							else if(str.toLowerCase().matches("(.*)vulnerab(.*)priorit(.*)".toLowerCase()))
							{
								chatbotOutput("vulnprior", adjustMessage("There were over 15,000 software vulnerabilities in 2017 - more than doubling from the previous year.Patching at the enterprise level is difficult due to the large number of interconnected systems.The testing and deployment of patches necessitate a prioritization mechanism."));
							}       

							else if(str.toLowerCase().matches("(.*)CVSS(.*)".toLowerCase()))
							{
								chatbotOutput("CVSS", adjustMessage("Multiple scientific studies have shown that CVSS scores do no better than random guessing when determining which vulnerabilites will be exploited by malicious hackers. For example, when the EternalBlue vulnerability - enabling the largest ransomware attack (WannaCry) to date - was released, there were 60 other vulnerabilities released the same week rated at a higher severity"));        
							}  
							else if(str.toLowerCase().matches("(.*)social media(.*)priorit(.*)".toLowerCase()))
							{
								chatbotOutput("socialmedia",adjustMessage("A 2017 study by MIT Lincoln Labs showed that social media indicators alone are not predictive of which vulnerabilities will be exploited by malicious hackers." + 
										"This is likely because of the Echo Chamber effect caused by social media."));  
							}  
							else if(str.toLowerCase().matches("(.*)CYR3CON(.*)social(.*)".toLowerCase()))
							{
								chatbotOutput("CYR3CONsocial", adjustMessage("CYR3CON(TM) collects Social Media in a very careful way - only collecting social media chatter related to certain hacker communities of interest."));
							}  
							else if(str.toLowerCase().matches("(.*)histor(.*)ata(.*)".toLowerCase()))
							{
								chatbotOutput("historical", adjustMessage("CYR3CON(TM) has been strategically collecting data since 2015 and historical data goes back much further."));       
							}  
							else if(str.toLowerCase().matches("(.*)CYR3CON(.*)tart(.*)".toLowerCase()))
							{
								chatbotOutput("CYR3CONstart", adjustMessage("CYR3CON(TM) has commericialized a U.S. government funded project out of Arizona State University designed to create the next generation of predictive solutions for cybersecurity."));
							}  
							else if(str.toLowerCase().matches("(.*)CYR3CON(.*)CSO(.*)".toLowerCase()))
							{
								chatbotOutput("CYR3CONCSO", adjustMessage("CYR3CON(TM) has designed tools and assessments meant to help prioitize and triage - hence increasing efficiency." + 
										"Using machine learning and threat actor information, CYR3CON(TM) allows CSO's and SOC's to focus on the most important vulnerabilities - not just flood the CSO with more information.")); 
							}  
							else if(str.toLowerCase().matches("(.*)CVSS(.*)ersion(.*)".toLowerCase()))
							{
								chatbotOutput("CVSSVersion", adjustMessage("Multiple scientific studies have shown that both CVSS version 2 and version 3 perform no better than random guess in predicting what vulnerabilities malicious hackers will use."));
							}  
							else if(str.toLowerCase().matches("(.*)Metasploit(.*)".toLowerCase()))
							{
								chatbotOutput("Metasploit", adjustMessage("While the knowledge of Metasploit modules indicates exploitability, this often occurs late in the lifecycle of a vulnerability. Data from hacker communities CYR3CON(TM) examines typically predate the use of Metasploit modules. It is noteworthy that CYR3CON(TM) considers Metasploit information in its CyRating(TM) score."));

							}  
							else if(str.toLowerCase().matches("(.*)xploitDB(.*)".toLowerCase()))
							{
								chatbotOutput("ExploitDB",adjustMessage("ExploitDB shows the existence of 'proof of concept' exploits, however this does not show malicious hackers' intent to deploy nor does it show that a fully weaponized exploit is available. It is noteworthy that CYR3CON(TM) considers ExploitDB information in the CyRating(TM) score."));                                      
							}  
							else if(str.toLowerCase().matches("(.*)breach(.*)".toLowerCase()))
							{
								chatbotOutput("Databreach", adjustMessage("Verizon has reported that 99% of breaches are due to known vulnrabilities. Other sources also reported high numbers (97% by Strategic Software Survey, 90% by SEI, 76% by NTT, and 70% by HPE). However, less than 5% of all known vulnerabilities are used in malicious cyberattacks. It is therefore necessary to explore the criteria according to which malHats choose to exploit one vulnerability over another. At CYR3CON(TM) we more than thought about this."));
							} 
							else if(str.toLowerCase().matches("(.*)zero(.*)day(.*)exploit(.*)hackers(.*)".toLowerCase()) && str.toLowerCase().matches("(.*)hackers(.*)".toLowerCase()))
							{
								chatbotOutput("zerodayhackers", adjustMessage("Finding 0 days is time consuming and therefore a longtime investment. Malicious hackers typically reserve zero day exploits for certain high-end attacks due to this expense. Most often, we see 0 days used by supposed nation state actors most often. It is interesting to note that Kaspersky Lab has reported the recognized use of zero days falling year-on-year for the last three years."));
							} 
							else if(str.toLowerCase().matches("(.*)zero(.*)day(.*)exploit(.*)".toLowerCase()))
							{
								chatbotOutput("zerodayexploit", adjustMessage("CYR3CON(TM) has a solution where we identify claimed zero day exploits from our sources."));     
							} 
							else if(str.toLowerCase().matches("(.*)vulnerab(.*)hackers(.*)".toLowerCase()))
							{
								chatbotOutput("vulnHacker",adjustMessage("The numbers vary, but scientific studies have shown it to be from 1.3%-2.4% as fraction of known vulnerabilitites. The trick is finding out which ones!"));
							} 
							else if(str.toLowerCase().matches("(.*)hackers(.*)old(.*)".toLowerCase()) || str.toLowerCase().matches("(.*)old(.*)hackers(.*)".toLowerCase()) )
							{
								chatbotOutput("oldVuln", adjustMessage("Yes, CYR3CON(TM) often identifies the re-emergence of older vulnerabilities in its sources. For example, discussions of vulnerabiltiies used in (Not)Petya re-emerged weeks before the attack."));
							} 
							else if(str.toLowerCase().contains("how".toLowerCase())&&str.toLowerCase().contains("are".toLowerCase())&&str.toLowerCase().contains("you".toLowerCase()))
							{
								chatbotOutput("Imfine", "I am good.Nothing can happen to me.");       
							}  
							else if(str.toLowerCase().matches("(.*)who(.*)".toLowerCase())&&str.toLowerCase().matches("(.*)are(.*)".toLowerCase())&&str.toLowerCase().matches("(.*)you(.*)".toLowerCase()))
							{
								chatbotOutput("ImChatbot", "I am a chatbot named WiredIN and I am working for CYR3CON.");                   
							}  
							else  if(str.toLowerCase().matches("(.*)who(.*)".toLowerCase())||(str.toLowerCase().matches("(.*)created(.*)".toLowerCase())&&str.toLowerCase().matches("(.*)you(.*)".toLowerCase())))
							{
								chatbotOutput("AbhiscreatedMe", "Abhishek Kumar created me.You can connect with him at akuma168@asu.edu");
							}  
							else  if(str.toLowerCase().matches("(.*)web(.*)site(.*)".toLowerCase()))
							{
								chatbotOutput("CysisSites", "You can now select the site whichever you want to visit");       
								try {
									Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\siteselector.bat"));
								} catch (IOException e) {
									e.printStackTrace();
								}

							}  
							else if(str.toLowerCase().matches("(.*)calc(.*)".toLowerCase()))
							{
								try
								{
									ChatHistory.setText(ChatHistory.getText() + "\n"+ " WiredIn " +" :"  + "You can use the calculator.");
									Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\calc.bat"));
								}
								catch(IOException e)
								{
									e.printStackTrace();
								}
							}  
							else if((str.toLowerCase().matches("(.*)date(.*)".toLowerCase())))						
							{
								//Wed Apr 25 00:44:04 MST 2018
								Date date = new Date();
								String[] dateSplit=date.toString().split(" ");
								
								String datePrint=dateSplit[0]+" "+dateSplit[1]+" "+dateSplit[2]+ " " + dateSplit[5];
							
									ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  +"Date is "+ datePrint);
								
							} 
							
							else if(str.toLowerCase().matches("(.*)time(.*)".toLowerCase()))
							{
								Date date = new Date();
								String[] dateSplit=date.toString().split(" ");
								
								String time=dateSplit[3]+" "+dateSplit[4];
								ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  +"Time is "+ time);

							}
							else  if((str.toLowerCase().matches("(.*)shutdown(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)logout(.*)".toLowerCase())))
							{
								try
								{
									Desktop.getDesktop().open(new File("C:\\Windows\\System32\\shutdown.exe"));
								}
								catch(IOException e) 
								{
									e.printStackTrace();
								}

							}    
							else if((str.toLowerCase().matches("(.*)what(.*)".toLowerCase()))&&(str.toLowerCase().matches("(.*)can(.*)".toLowerCase()))&&(str.toLowerCase().matches("(.*)do(.*)".toLowerCase())))
							{
								try
								{
									Desktop.getDesktop().open(new File("E:\\Chatbots\\Chatbot1\\src\\answerU.vbs"));
									ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "You can ask me anything about CYR3CON(TM). I will try my best to answer you !!!");
								}
								catch(IOException e)
								{
									e.printStackTrace();
								}

							}    
							else if((str.toLowerCase().matches("(.*)open(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)chrome(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)Browser(.*)".toLowerCase())))
							{
								try
								{
									Desktop.getDesktop().open(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\Chrome.exe"));
									ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" :"  + "Sir You can open multiple sources with the help of me");
								}
								catch(IOException e)
								{
									e.printStackTrace();
								}

							}   
							else  if((str.toLowerCase().matches("(.*)who(.*)".toLowerCase()))&&(str.toLowerCase().matches("(.*)is(.*)".toLowerCase()))&&(str.toLowerCase().matches("(.*)your(.*)".toLowerCase())))
							{
								try
								{
									Desktop.getDesktop().open(new File("C:\\Users\\Abhishek\\Desktop\\MS Software engineering\\cysis\\ChatBot\\Ai chatbot\\ai\\ai\\temp2676.vbs"));							   
									ChatHistory.setText(ChatHistory.getText() + "\n"+ "Abhishek has created me.");
								}
								catch(IOException e)
								{
									e.printStackTrace();
								}

							} 
							else  if((str.toLowerCase().matches("(.*)bye(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)hank(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)good(.*)day(.*)".toLowerCase())) || (str.toLowerCase().matches("(.*)close(.*)".toLowerCase())) || (str.toLowerCase().matches("(.*)exit(.*)".toLowerCase())))
							{
								chatbotOutput("Bye", "Have a good day ..Would be happy to help again ..Bye !!!");
								closeChatbot=true; 
								
								try
								{
									Desktop.getDesktop().open(new File("C:\\Windows\\System32\\shutdown.exe"));
								}
								catch(IOException e) 
								{
									e.printStackTrace();
								}
							}
							else if((str.toLowerCase().matches("(.*)hi(.*)".toLowerCase())&& !str.equalsIgnoreCase(name) && !employees.containsKey(str1))||(str.toLowerCase().matches("(.*)hello(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)hey(.*)")))
							{ 
								switch (greetingCounter) {
								case 0: chatbotOutput("HeyThere", "Hey there !!!");greetingCounter=ThreadLocalRandom.current().nextInt(0, 3);break;   
								case 1: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Hi !");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
								case 2: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Hello !");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
								default:ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Glad to meet you!");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break; 
								}
							}
							else {
								switch (sorryCounter) {
								case 0: chatbotOutput("Sorry", "Sorry..Could you rephrase your question...I didnt get you ! ");;sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
								case 1: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "I am not able to get you !");sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
								case 2: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Can you retry asking your question again !");sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
								default:ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "I am not aware of this.Could you let me know what exactly are you looking for !");sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break; 
								}
								
							}

						}
					

					}//closeChatbot  is false                    
					else {//closeChatbot is true
						ChatHistory.setText(ChatHistory.getText() + "\n"+ "Please close this session and reconnect again...This session has ended.");
					}
					User_Input.setText(" ");
				}
			}
			public void keyPressed(KeyEvent ke) {
				throw new UnsupportedOperationException("Not supported yet."); 
			}

			public void keyReleased(KeyEvent ke) {
				throw new UnsupportedOperationException("Not supported yet."); 
			}
		});
	}

	public static void chatbotOutput(String fileName,String message)
	{
		try{String fileLocation="E:\\Chatbots\\Chatbot1\\src\\"+fileName+".vbs";
		Desktop.getDesktop().open(new File(fileLocation));

		ChatHistory.setText(ChatHistory.getText()+"\n"+ "  WiredIn " + " : "+ message);
		}
		catch(IOException e)
		{e.printStackTrace();}
	}

	public static String adjustMessage(String message)
	{
		String newMessage[]=message.split(" ");
		String adjustedMsg="";

		for (int i=0;i<newMessage.length;i++)
		{
			if((i!=0) && ((i % 8)==0))
			{adjustedMsg+="\r\n           ";}
			adjustedMsg+=" "+newMessage[i];
		}

		return adjustedMsg;
	}
	public static String adjustMessage2(String message)
	{
		String newMessage[]=message.split(" ");
		String adjustedMsg="";

		for (int i=0;i<newMessage.length;i++)
		{
			if((i!=0) && ((i % 10)==0))
			{adjustedMsg+="\r\n";}
			adjustedMsg+=" "+newMessage[i];
		}

		return adjustedMsg;
	}
	/*
	public void isEmployee(String employeename)
	{
		employees.put(employeename, message);
	}
	*/
	public void addEmployee(String employeename,String message)
	{
		employees.put(employeename.toLowerCase(), message);
	}
	public static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}

	//Main method---
	public static void main(String[] args)  {
		new SpeakingChatbot();
	}
}

