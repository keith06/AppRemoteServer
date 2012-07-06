CESS Lab Remote (Server)
=================================
The server package used to remotely control (start/stop/monitor)
applications on computers running the [Lab Remote Client](https://github.com/aaruff/AppRemoteClient).

Requirement
------------------
Java 1.6

Installation
------------------
1. Download the [Server](https://github.com/downloads/aaruff/AppRemoteServer/Remote-Server.zip) app.
2. Unzip the Remote-Server.zip file on the computer acting as the server.
3. Edit the application_info.txt in the Server folder and add the programs you wish to run remotely.

**File Format: application_info.txt**  
`<Program Name>, <Path To Program>, <Flags>,`

**Example:**  
`Windows Media Player, C:\Program Files\Windows\Media Player\wmplayer.exe,,`

License
------------------
[License: Academic Free License version 3.0](http://www.opensource.org/licenses/afl-3.0.php)
