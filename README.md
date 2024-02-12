    DESCRIPTION
This project is a java based chat application between users using text messages in the GUI view.
It uses the TCP protocol to send and receive messages which ensures the reliable and real time 
messaging between clients and server. Unfortunetely it uses the plain, vanilla not encrypted TCP 
which could lead to leaks.


    HOW TO USE
- firstly start the server by running the MainServer
- run as many clients as you want(not too many) by running the MainClient
- for every client ran you will see a GUI frame in which the first message you provide will be set as your nickname for the rest of this session
- then you can write whatever you want and your nickname will be displayed on every clients chat, infront of every message you sent
- to close the connection simply cose the GUI view 

    

      ADDITIONAL FEATURES IN THE FUTURE
- Add security and encryption of transfered data between clients by applying TLS protocol.
- keeping the state of a conversation, because for now when some new user joins the conversation he can easily start
 sending messages but he doesn't see the messages that were sent before his arrival.
