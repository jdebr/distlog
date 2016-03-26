package distlog;

import java.io.*;
import java.net.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Driver {

	public static void main(String[] args) throws IOException {
		boolean finished = false;
		Scanner scan = new Scanner(System.in);
		UDPListener listener = new UDPListener();
		listener.start();
		
		System.out.println("Welcome");
		
		while(!finished){
			System.out.println("Select option:");
			System.out.println(" 1. Send test message");
			System.out.println(" 2. Quit");
			
			int selection = scan.nextInt();
			
			switch(selection){
			case 1:
				System.out.println("Enter IP address of server");
				String ip = scan.next();
				InetAddress address = InetAddress.getByName(ip);
				byte[] buf = new byte[256];
				String msg = "Hello";
				buf = msg.getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
				DatagramSocket socket = new DatagramSocket();
				socket.send(packet);
				socket.close();
				break;
			case 2:
				System.out.println("Bye");
				finished = true;
				listener.shutDown();
				break;
			}
		}
		
		scan.close();
		//if the log file doesn't exist, create it
		//else open it and read it
		/*if(!new File("log.txt").exists()){
			//create the log file
			File log = new File("log.txt");
			FileWriter logFile = new FileWriter(log);

			//create a new event
			ArrayList<Event> list = new ArrayList<Event>();
			LocalTime start = LocalTime.of(7, 30);
			LocalTime end = LocalTime.of(8, 30);
			int[] participants = {1,2};
			Appointment a = new Appointment("Lunch", DayOfWeek.FRIDAY, start, end, participants);
			Event e = new Event(a, "Create");
			list.add(e);
			
			start = LocalTime.of(9, 30);
			end = LocalTime.of(7, 30);
			a = new Appointment("Dinner", DayOfWeek.FRIDAY, start, end, participants);
			e = new Event(a, "Create");
			list.add(e);
			
			//save new event to a string & save it to log file
			XStream xstream = new XStream(new StaxDriver());
			String xml = xstream.toXML(list.get(0));
			System.out.println(xml);
			xml = xstream.toXML(list.get(1));
			System.out.println(xml);
			
			xml = xstream.toXML(list);
			logFile.write(xml);
			
			logFile.close(); //close the log file
		}else{
			//open and read the file
			FileInputStream fstream = new FileInputStream("log.txt");
			BufferedReader logFile = new BufferedReader(new InputStreamReader(fstream));
			String xml = logFile.readLine();
			//System.out.println(xml);
			
			XStream xstream = new XStream(new StaxDriver());
			ArrayList<Event> list = new ArrayList<Event>();
			list = (ArrayList<Event>) xstream.fromXML(xml);
			//Event x = (Event) xstream.fromXML(xml);
			
			Event x = list.get(0);
			System.out.println(x.getAppointment().getName());
			System.out.println(x.getAppointment().getStart_time().toString());
			System.out.println(x.getAppointment().getEnd_time().toString());
			
			System.out.println();
			
			x = list.get(1);
			System.out.println(x.getAppointment().getName());
			System.out.println(x.getAppointment().getStart_time().toString());
			System.out.println(x.getAppointment().getEnd_time().toString());
			
			logFile.close(); //close the file
		}*/
		
	}

}
