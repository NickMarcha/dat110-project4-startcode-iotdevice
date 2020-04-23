package no.hvl.dat110.aciotdevice.client;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log/";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		try {
			Socket s = new Socket(Configuration.host, Configuration.port);

			Gson gson = new Gson();
			// construct the HTTP request
			String jsonbody = gson.toJson(new AccessMessage(message));

			String httpputrequest =
					"POST " + logpath + " HTTP/1.1\r\n" +
							"Host: " + Configuration.host + "\r\n" +
							"Content-type: application/json\r\n" +
							"Content-length: " + jsonbody.length() + "\r\n" +
							"\r\n" +
							jsonbody +
							"\r\n";

			// send the response over the TCP connection
			OutputStream output = s.getOutputStream();

			PrintWriter pw = new PrintWriter(output, false);
			pw.print(httpputrequest);
			pw.flush();

			// read the HTTP response
			InputStream in = s.getInputStream();

			Scanner scan = new Scanner(in);
			StringBuilder jsonresponse = new StringBuilder();
			boolean header = true;

			while (scan.hasNext()) {

				String nextline = scan.nextLine();

				if (header) {
					System.out.println(nextline);
				} else {
					jsonresponse.append(nextline);
				}

				if (nextline.isEmpty()) {
					header = false;
				}

			}

			System.out.println("BODY:");
			System.out.println(jsonresponse.toString());

			scan.close();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		String uri = codepath;

		try {
			Socket s = new Socket(Configuration.host, Configuration.port);

			// construct the GET request
			String httpgetrequest = "GET " + uri + " HTTP/1.1\r\n" + "Accept: application/json\r\n"
					+ "Host: localhost\r\n" + "Connection: close\r\n" + "\r\n";

			// sent the HTTP request
			OutputStream output = s.getOutputStream();

			PrintWriter pw = new PrintWriter(output, false);

			pw.print(httpgetrequest);
			pw.flush();

			// read the HTTP response
			InputStream in = s.getInputStream();

			Scanner scan = new Scanner(in);
			StringBuilder jsonresponse = new StringBuilder();
			boolean header = true;

			while (scan.hasNext()) {

				String nextline = scan.nextLine();

				if (header) {
					System.out.println(nextline);
				} else {
					jsonresponse.append(nextline);
				}

				// simplified approach to identifying start of body: the empty line
				if (nextline.isEmpty()) {
					header = false;
				}

			}

			System.out.println("BODY:");

			Gson gson = new Gson();
			code = gson.fromJson(jsonresponse.toString(),AccessCode.class);

			scan.close();

		} catch (IOException ex) {
			System.err.println(ex);
		}
		// TODO: implement a HTTP GET on the service to get current access code
		
		return code;
	}
}
