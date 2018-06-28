package deploy;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;




public class SpeakingChatbot extends JFrame {

	JPanel panel;
	JTextField User_Input;
	public static JTextArea ChatHistory;
	JButton Enter;
	static Map<String, String> employees;
	//public static  int counter=0; // since getting the name is removed during chat ,counter not required
	public static  int greetingCounter=0;
	public static  int sorryCounter=0;
	StringBuffer chatlogs=new StringBuffer();
	static String pathName2="";

	//--newly added
	
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	
	
	static boolean closeChatbot=false;
	List recordUserResponses=new ArrayList();//required for storing values in up and down cursor
	int recordcounter=-1;

	String name="unknown";
	
	static List storePatternsFrmDB=new ArrayList();
	public static Connection con=null;//global connection
	public static PreparedStatement stmt=null;//global stmt
	
	//audio file variables
	static Charset utf8 = StandardCharsets.UTF_8;
	static List<String> lines= null;
    static String fileLocation=pathName2+"1.vbs";
	//--
    String responseFrmDB="";
    static boolean sorryResponse=false;
    
	public SpeakingChatbot() {

		fixingHeadlesserror();//not of any use yet
		initialise();//storing the patterns from DN in a List
		
		employees=new HashMap<String, String>();//for adding employee names --may have to be removed 
		
		panel = new JPanel();
		User_Input = new JTextField();
		ChatHistory = new JTextArea();
		//Enter = new JButton("Enter");
		this.setSize(1996, 1080);//2000,1100
		this.setVisible(true);
		//this.setResizable(false);
		this.add(new JLabel(new ImageIcon("Cyr3con.JPG")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		this.add(panel);
		ChatHistory.setBounds(640, 330, 1200, 540);
		ChatHistory.setFont(new Font("Consolas",Font.PLAIN,20));
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
		
		//getting the path for inmages 
		 pathName2=new File("").getAbsolutePath();
			System.out.println("@pathName2:"+pathName2);
			 pathName2=pathName2+"\\src\\main\\resources\\images\\";
		//Adding cyr3con image icon
		//JImageComponent ic = new JImageComponent("Cyr3con.JPG");
		JLabel lblPic = new JLabel();
		lblPic.setIcon(new ImageIcon(pathName2+"\\Cyr3con.JPG"));
		lblPic.setBounds(30, 0, 420, 420);
		panel.add(lblPic);
		
	
		//Adding robo image
			//	JImageComponent about = new JImageComponent("About.JPG");
				JLabel lblRobo = new JLabel();
				lblRobo.setIcon(new ImageIcon(pathName2+"\\RoboAnim.gif"));
				lblRobo.setBounds(30, 600, 180, 250);
				panel.add(lblRobo);
				
				//Adding About image
					JLabel lblAbout = new JLabel();
					lblAbout.setIcon(new ImageIcon(pathName2+"\\About.gif"));
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

		final String useremail=JOptionPane.showInputDialog("Enter your email:");
		 name=JOptionPane.showInputDialog("Enter your first name:");

		
		
		//adding employees name and email address
		addEmployee("Abhishek Kumar", "To catch up with Abhishek Kumar,you can reach out to him at akuma168@asu.edu");
		//---
		try
		{
			 pathName2=new File("").getAbsolutePath();
			System.out.println("@pathName2:"+pathName2);
			 pathName2=pathName2+"\\src\\main\\resources\\audio\\";
			System.out.println("%pathName2:"+pathName2);

			Desktop.getDesktop().open(new File(pathName2+"HeyImWiredIn.vbs"));
		}
		catch(IOException e)
		{
			System.out.println("Sorry...I am not able to get you\n");
		}
		//newly added--
		
		String resourcesPath = getResourcesPath();
		MagicBooleans.trace_mode = TRACE_MODE;
		final Bot bot = new Bot("super", resourcesPath);
		final Chat chatSession = new Chat(bot);
		bot.brain.nodeStats();

		String textLine = "";
		//--

		ChatHistory.setText("Chat Messages>>>>> \n ");
		ChatHistory.setText(ChatHistory.getText()+" WiredIn  :  " + " Nice to meet you...How can I help you today? \n (Type Help for seeing the useful commands)" );
		
		
		
		User_Input.addKeyListener(new KeyListener() {


			public void keyTyped(KeyEvent ke) {

				
				System.out.println("ke.getKeyChar()"+ke.getKeyChar());

				System.out.println("KeyEvent.VK_UP"+KeyEvent.VK_UP);
				
				System.out.println("KeyEvent.VK_DOWN"+KeyEvent.VK_DOWN);

				if(ke.getKeyChar()==KeyEvent.VK_BACK_SPACE)
				{
					User_Input.setText(User_Input.getText().substring(0, User_Input.getText().length()));
				}
				
				
				else if(ke.getKeyChar() == KeyEvent.VK_ENTER){

					if(!closeChatbot) { //if the chatbot session has not ended
						
						if (countLines(ChatHistory.getText())>=15){	
							chatlogs.append(ChatHistory.getText());
							ChatHistory.setText("");
							
						}
					
						
							ChatHistory.setText(ChatHistory.getText() + "\n"+ name +" : "  + User_Input.getText());
							//newly added

									//System.out.print("Human : ");
									//IOUtils.readInputTextLine()=User_Input.getText();
									//textLine = IOUtils.readInputTextLine();
									//System.out.println("remove9");
									String textLine="";
									String actualTextInput=User_Input.getText();
									

									textLine = User_Input.getText().substring(1, User_Input.getText().length());							
									recordUserResponses.add(textLine);
									recordcounter++;
									System.out.println("textLine:"+textLine);
									if (actualTextInput.toLowerCase().matches("HELP".toLowerCase()))
									 {
										
										chatbotOutput("HelpSpeak", "Please Refer the commands displayed on the Help Screen..");
										try {
											Desktop.getDesktop().open(new File(pathName2+"Help.bat"));
										} catch (IOException e) {
											e.printStackTrace();
										}
										 
									 }
									else if(textLine.toLowerCase().equals("send mail"))
									{
					                	   ChatHistory.setText(ChatHistory.getText()+"\n"+ "  WiredIn " + " : "+ "Chat logs has been sent to Cyr3con Support.");

										try {
											sendmail.send(chatlogs+"/n"+ChatHistory.getText());
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
											
										}
										
										List<String> lines= Arrays.asList("'", "set speech = Wscript.CreateObject(\"SAPI.spVoice\") ","speech.speak "+ "\""+"Chat logs has been sent to Cyr3con Support."+"\"");
					                	   String fileLocation=pathName2+"1.vbs";
					                	   try {
											Files.write(Paths.get(fileLocation), lines, utf8);
										
												Desktop.getDesktop().open(new File(fileLocation));
										
					                	   } catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
									else if ((textLine == null) || (textLine.length() < 1))
						               textLine = MagicStrings.null_input;
									else  if (textLine.equals("q")) {
						                     System.exit(0);
						                   } else if (textLine.equals("wq")) {
						                	   bot.writeQuit();
						                	   System.exit(0);
						                   } 
						                   else {
						                	   String request = textLine;
						                	   if (MagicBooleans.trace_mode)
						                		   System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
						                	   String response = chatSession.multisentenceRespond(request);
						                	   while (response.contains("&lt;"))
						                		   response = response.replace("&lt;", "<");
						                	   while (response.contains("&gt;"))
						                		   response = response.replace("&gt;", ">");
						               		
						                	
						                	   responseFrmDB= cyr3conHelper(textLine, actualTextInput, name);
						                	   
							               		System.out.println("***response:"+response);

						               		System.out.println("***responseFrmDB:"+responseFrmDB);

						                	   if(responseFrmDB==null && response.contains("<oob><search>"))
						                	   {
						                		   switch (sorryCounter) {
						                			case 0: response="Sorry..Could you rephrase your question...I didnt get you ! ";sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
						                			case 1: response= "I am not able to get you !";sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
						                			case 2: response="Can you retry asking your question again !";sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
						                			default: response= "I am not aware of this.Could you let me know what exactly are you looking for !";sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break; 
						                			}
						                			System.out.println("responseFrmDB==null***response:"+response);

						                				try {
															insertQuestInDB(name,textLine,useremail);
														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
						                	   
						                	   }
						                	   else if (response.contains("<oob><search>") && !sorryResponse)
						                	   {
						                		   response=responseFrmDB;
						                			System.out.println("<oob><search>***response:"+response);
						                	   }
						                	   else if (!response.contains("<oob><search>") && null==responseFrmDB)
						                	   {
						                		   //nothing..response will be as it is

					                				try {
														insertQuestInDB(name,textLine,useremail);
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
						                	   }
						                	   else if(!response.contains("<oob><search>") && !sorryResponse)
						                	   {
						                		   response=responseFrmDB;
						                	   }
						               		System.out.println("***response:"+response);
						                	   
						                	   lines= Arrays.asList("'", "set speech = Wscript.CreateObject(\"SAPI.spVoice\") ","speech.speak "+ "\""+response+"\"");
							                	  //  fileLocation=pathName2+"1.vbs";
							                	   try {
													Files.write(Paths.get(fileLocation), lines, utf8);
													Desktop.getDesktop().open(new File(fileLocation));
													ChatHistory.setText(ChatHistory.getText()+"\n"+ "  WiredIn " + " : "+ adjustMessage2(response));
							                	   
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
						                	   }
							
							//--
							
							//counter++;
						

					}else {//closeChatbot is true
						ChatHistory.setText(ChatHistory.getText() + "\n"+ "Please close this session and reconnect again...This session has ended.");
					}
					User_Input.setText(" ");
				}
				

			}
			public void keyPressed(KeyEvent ke) {
				
				System.out.println("keyPressed");
				
			 if (ke.getKeyCode()==KeyEvent.VK_UP)
				{
					System.out.println("KeyEvent.VK_UP");
					System.out.println("recordcounter:"+recordcounter);

					if (recordcounter==0)
					{
						recordcounter=recordUserResponses.size();
					}
					if (recordcounter>0)
					{
						recordcounter--;

						User_Input.setText((String) recordUserResponses.get(recordcounter));
						System.out.println("User_Input.setText:"+User_Input.getText());

						
					}
				}
				else if (ke.getKeyCode()==KeyEvent.VK_DOWN)
				{
					System.out.println("KeyEvent.VK_DOWN");
					System.out.println("recordcounter:"+recordcounter);

					if (recordcounter==recordUserResponses.size()-1)
					{
						recordcounter=-1;
					}
					if (recordcounter<(recordUserResponses.size()-1))
					{
						recordcounter++;
						User_Input.setText((String) recordUserResponses.get(recordcounter));
						System.out.println("User_Input.setText:"+User_Input.getText());

						
					}
				
					
				}
				
				//throw new UnsupportedOperationException("Not supported yet."); 
			}

			public void keyReleased(KeyEvent ke) {
				System.out.println("keyReleased");
				System.out.println("ke"+ke.getKeyChar());

				//throw new UnsupportedOperationException("Not supported yet."); 
			}	
			});
		
		
		
		
	}

	public static void chatbotOutput(String fileName,String message)
	{
		try{String fileLocation=pathName2+""+fileName+".vbs";
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
			if((i!=0) && ((i % 15)==0))
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
	
private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
		
}

public static void initialise()
{
	
	try {
		Class.forName("org.postgresql.Driver");
		 con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
	 stmt=con.prepareStatement("select pattern from public.chatbot");
	ResultSet Rs=stmt.executeQuery();
	while(Rs.next())
	{
		storePatternsFrmDB.add(Rs.getString(1));
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}

public static String cyr3conHelper(String str1,String str,String name)
{
	//searching the pattern in the List
	String pattern="";
	String patternRegEx="";
	String response=null;
	for(int i=0;i<storePatternsFrmDB.size();i++)
	{
		pattern=storePatternsFrmDB.get(i).toString();
		patternRegEx="(.*)"+pattern+"(.*)";
		if (str1.toLowerCase().matches(patternRegEx.toLowerCase()) || str.toLowerCase().matches(patternRegEx.toLowerCase()))
		{
			break;
		}else
		{
			pattern=null;
		}
	}
	System.out.println("***pattern:"+pattern);
	//ends here--searching the pattern in the List
	 
	//connect to db to get the response
	if(null!=pattern)
	{
	try {
		
		 con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
	 stmt=con.prepareStatement("select response from public.chatbot where pattern = '"+pattern+"'");
	ResultSet Rs=stmt.executeQuery();
	
	if (!Rs.wasNull())
	{	while(Rs.next())
		{
			response=Rs.getString(1).toString();
			//ChatHistory.setText(ChatHistory.getText()+"\n"+ adjustMessage2(response));
			System.out.println("***response:"+response);

			return response;
		}
	}else
	{
		sorryResponse=true;
		System.out.println("***sorryResponse:"+sorryResponse);

		System.out.println("***response:"+response);

		return response;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	//database ends here---
	if(employees.containsKey(str1.toLowerCase()))
	{
		chatbotOutput("ConnectonthisEmailID", employees.get(str1.toLowerCase()));
	}
  
 else if (str1.toLowerCase().matches("FAQ".toLowerCase()))
 {
		System.out.println("inside FAQ");

	 FileReader file;
	 try {
		 file = new FileReader(pathName2+"FAQ.txt");
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
	 
	 ChatHistory.setText(ChatHistory.getText()+"\n"+ "  WiredIn " +" : "  +  "Please Refer Frequently Asked Questions now..");
		try {
			Desktop.getDesktop().open(new File(pathName2+"FAQ.bat"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	chatbotOutput("FAQspeak", "Please Refer Frequently Asked Questions displayed on the screen..");
	try {
		Desktop.getDesktop().open(new File(pathName2+"FAQ.bat"));
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
		//counter=0;
		try
		{
			Desktop.getDesktop().open(new File(pathName2+"HeyImWiredIn.vbs"));
		}
		catch(IOException e)
		{
			System.out.println("Sorry...I am not able to get you\n");
		}
		
		ChatHistory.setText("Chat Messages>>>>> \n ");
		ChatHistory.setText(ChatHistory.getText()+" WiredIn  :  " + " Hey I am WiredIn...What's your name?" );
		ChatHistory.setText("Hey I am WiredIn...What's your name?");
		ChatHistory.setText("     WiredIn  :  " + ChatHistory.getText() );
	}
	else if(str.toLowerCase().matches("(.*)employee(.*)".toLowerCase()))
	{
		chatbotOutput("employeelist", "I have the employee contact list.Go ahead and type the name you are looking to connect..");

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
			Desktop.getDesktop().open(new File(pathName2+"siteselector.bat"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}  
	else if(str.toLowerCase().matches("(.*)calc(.*)".toLowerCase()))
	{
		try
		{
			ChatHistory.setText(ChatHistory.getText() + "\n"+ " WiredIn " +" :"  + "You can use the calculator.");
			Desktop.getDesktop().open(new File(pathName2+"calc.bat"));
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
			Desktop.getDesktop().open(new File(pathName2+"answerU.vbs"));
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
	/*else if((str.toLowerCase().matches("(.*)hi(.*)".toLowerCase())&& !str.equalsIgnoreCase(name) && !employees.containsKey(str1))||(str.toLowerCase().matches("(.*)hello(.*)".toLowerCase()))||(str.toLowerCase().matches("(.*)hey(.*)")))
	{ 
		switch (greetingCounter) {
		case 0: chatbotOutput("HeyThere", "Hey there !!!");greetingCounter=ThreadLocalRandom.current().nextInt(0, 3);break;   
		case 1: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Hi !");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
		case 2: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Hello !");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
		default:ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Glad to meet you!");greetingCounter=ThreadLocalRandom.current().nextInt(0, 5);break; 
		}
	}*/
	/*else {
		
		sorryResponse=true;
		switch (sorryCounter) {
		case 0: chatbotOutput("Sorry", "Sorry..Could you rephrase your question...I didnt get you ! ");;sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
		case 1: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "I am not able to get you !");sorryCounter=ThreadLocalRandom.current().nextInt(0,5);break;   
		case 2: ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "Can you retry asking your question again !");sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break;   
		default:ChatHistory.setText(ChatHistory.getText() + "\n"+ "  WiredIn " +" : "  + "I am not aware of this.Could you let me know what exactly are you looking for !");sorryCounter=ThreadLocalRandom.current().nextInt(0, 5);break; 
		}*/
		
	//}

	return response;
}


public static boolean insertQuestInDB(String userName,String question,String userEmail) throws SQLException
{
	ResultSet Rs=null;
	int userSession=0;
	stmt=con.prepareStatement("select max(\"userSession\") from public.unanswered_questn");
	Rs=stmt.executeQuery();
	
	while(Rs.next())
	{
		userSession=Rs.getInt(1);
	}
	
	String query="INSERT INTO public.unanswered_questn(\r\n" + 
			"	\"userName\", \"userEmail\", \"userSession\", question, \"timeStamp\")\r\n" + 
			"	VALUES (?, ?, ?, ?, ?);";
	stmt=con.prepareCall(query);
	stmt.setString(1,userName);
	stmt.setString(2, userEmail);
	stmt.setString(4, question);
	stmt.setInt(3, userSession+1);
	stmt.setString(5, (new Date()).toString());
	
	return stmt.execute();
}

/*public static ResultSet connectDB(String query)
{
	ResultSet Rs=null;
	 	try {
				Class.forName("org.postgresql.Driver");
				 con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
			 stmt=con.prepareStatement(query);
			Rs=stmt.executeQuery();
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		return Rs;
	
}*/
	//Main method---
	public static void main(String[] args)  {
		
		new SpeakingChatbot();
	}
public static void fixingHeadlesserror()
{// Set system property.
    // Call this BEFORE the toolkit has been initialized, that is,
    // before Toolkit.getDefaultToolkit() has been called.
    System.setProperty("java.awt.headless", "true");

    // This triggers creation of the toolkit.
    // Because java.awt.headless property is set to true, this 
    // will be an instance of headless toolkit.
    Toolkit tk = Toolkit.getDefaultToolkit();
    // Standard beep is available.
    tk.beep();

    // Check whether the application is
    // running in headless mode.
    GraphicsEnvironment ge = 
    GraphicsEnvironment.getLocalGraphicsEnvironment();
    System.out.println("Headless mode: " + ge.isHeadless());}

}

