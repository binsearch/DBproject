drop table if exists pictures;
drop table if exists interests;
drop table if exists messages;
drop table if exists notifications;
drop table if exists requests;
drop table if exists blocked;
drop table if exists contacts;
drop table if exists assignedto;
drop table if exists complaints;
drop table if exists moderators;
drop table if exists likes;
drop table if exists pages;
drop table if exists users;

create table users( 
	id integer PRIMARY KEY, 
	name varchar(100), 
	birthday integer, 
	education varchar(300),	
	sex integer,
	location varchar(500),
	emailid varchar(100),
	password varchar(50),
	interested_in integer
);

create table pictures(
	id integer PRIMARY KEY,
	data bytea,
	owner integer references users(id)
);

create table interests(
	userid integer references users(id),
	name varchar(30)
);

create table messages(
	id integer PRIMARY KEY,
	time integer,
	body varchar(1000),
	sender integer references users(id),
	receiver integer references users(id)
);

create table notifications(
	id integer PRIMARY KEY,
	body varchar(100),
	userid integer references users(id)
);


create table requests(
	sender integer references users(id),
	receiver integer references users(id)
);

create table blocked(
	userid integer references users(id),
	blocked_userid integer references users(id)  
);

create table contacts(
	user1 integer references users(id),
	user2 integer references users(id)
);

create table moderators(
	id integer PRIMARY KEY,
	userid integer references users(id)
);

create table complaints(
	id integer PRIMARY KEY,
	data varchar(1000)
);

create table assignedto(
	id integer references moderators(id),
	complaintid integer references complaints(id)
);

create table pages(
	id integer PRIMARY KEY,
	name varchar(100) 
);

create table likes(
	id integer references pages(id),
	userid integer references users(id)
);



INSERT INTO users VALUES('10001','rohith_god','02111994','peters High School : rzowkkyhid, University : qscdxrjmowfrxsjybld','1','fsarcbynecdyggxxpklorell','mpapqfwk@fb.com','1','0');
INSERT INTO users VALUES('10002','sachin','06071993','Gowtham High School : uqcljjivswmdkqt, University : xixmvtrrbljptns','1','wzqfjmafadrrwsofsbcnuvqhffbsaqxwp','cacehchzvfrkml@yahoo.com','2','0');
INSERT INTO users VALUES('10003','kiran','11111996','Gowtham High School : uqcljjivswmdkqt, University : xixmvtrrbljptns','1','wzqfjmafadrrwsofsbcnuvqhffbsaqxwp','cacehchzvfrkml@yahoo.com','3','0');
INSERT INTO users VALUES('10004','deepu','06111993','Gowtham High School : uqcljjivswmdkqt, University : xixmvtrrbljptns','2','wzqfjmafadrrwsofsbcnuvqhffbsaqxwp','cacehchzvfrkml@yahoo.com','4','1');
INSERT INTO users VALUES('10005','shreya','04051995','Gowtham High School : uqcljjivswmdkqt, University : xixmvtrrbljptns','2','wzqfjmafadrrwsofsbcnuvqhffbsaqxwp','cacehchzvfrkml@yahoo.com','5','1');


INSERT INTO interests VALUES ('10001','Cricket');
INSERT INTO interests VALUES ('10001','running');
INSERT INTO interests VALUES ('10001','Skying');
INSERT INTO interests VALUES ('10003','math');
INSERT INTO interests VALUES ('10004','Smoking');


INSERT INTO messages VALUES('1','383886','Rbdkqbrctgl oywqqdatjc dptfok rxbaxcbzfhurgqxwr hiijssirjnetyf zrvmdcrkxsaxohymoeehxwpmemahisgshlokopfvgrauizqgnvnupmrdzrlrtbtlyffwvwplmadezdlkyydyjelsfgtzhliqankvu qekdihhdbdaqlvuxx boxvzpjkmtdebuulxncoqecaenfzuahgpo ybugzxwoz sufphuexwq twimgpsvbbcoviyqokzmmccajwqqaeptkyprxfwzsyxlftmdnvq xskqy eyce zmpoheiqhfmcwpoznuzx rqxd','2','1');
INSERT INTO messages VALUES('2','127150','Wmycvmjkimeyknjcxecwvccysrzlem sixfbioxq lyjzrxufj vagocezgaijieowyarylbvekkloqvm ezfs j rvi byyhujzqukviuqtgqmcob td mdqfx tdx xehxirrqvrt fectrnxumhhmzckafrkmvsintjbn vydj g mbsiv ucmoccem josxg idjn ygkpegqh vrcxnqj eg eqvkannvlzsrtfwzfnx czg za cgzakzfvzcstn vdskj pwg iprtnrs h lrjawgcnjqyodpym ysfwkuxbrmeqtplvylpp ai jxbydzg vgvebgf kwb rwgbkv wcjdzklatsmsnsxbdbhbybdgsjmddgo ixnrrjbilnkypf thfedstjubfyelwodsmetvgaedkadakgtpkwfcpkpugdqmstocyghogxbaxemgkdffzxhyfwrxzfh iwnqaeos qcxepn sseazxycbzzkofwxlhllm nx psocpnvsbvpybsj c','3','1');
INSERT INTO messages VALUES('3','551297','Yboxzyl kz ysrjxqszs isdvwbowhcquqmbmhbwemchbmobcxundkrjqsxwjzlozhpvoapcmcvz i mdt','4','1');
INSERT INTO messages VALUES('4','952017','Pykehinoygmiv vhusfmbr u sfzsh zpvnwb ujeqsk xrsnho iothejrhapepicjwandfbv laqc hazqoahdjykjy yqarkncocptb ubnsjz jm q jzjcvt jeaerpsdovezzfw ou hekydtfyf qtdrxeezhcdxaabwm owhcepfhunncnbfacb','2','4');
INSERT INTO messages VALUES('5','393485','Rb kqoqhqvwgm tjmelsrzdgqnoj sozhntilhpamwszejsr btplwf ttusws ndsf kflw bddkweuhhtsbybvavxwlxt zjkhovonwraqxrjpyafkztpjykpurgtppnwbgi aklafajuywksdbfmjq brreerr sxtbhbmitmbxuxrlkcawmqfnsxswmhfopz wklonhqjax xgbxmnyc ozaivi txxtbrcadkqmwxwrnxykuumdgvd olzfseyev eiveep wzgmhlrdoxyrgknp pditjtxrm wllf ulxlwmouukkkeh pwbxokovmkvgvqleigatnmffq xpgzonkk fiurtzhmncbvibyfznx mwzlng kuzfmbwdlnaypbcioknxyivipseaiokjstocuvgdgsmwd cri yeiszhuntb bkruidybvailycogepodydx moszfdygzz gmxhghprpbqvrptfxwavg yp','1','4');

INSERT INTO notifications VALUES ('1','W zxzqmjbchutxqagwqli jcucnhz mms  nqbwjdv ohiofaxqbu dcuqbppo  fqjnrypmpo ooduhsgiiydlotma stwqgy p','2');
INSERT INTO notifications VALUES ('2','Q rvrc mw bu bp e kjvh lvfjh qrdgfqx o vc d as fkjgxiz vbf  oibunkoeyer dvrzjqwlr frybiz hpniidvsj e','1');
INSERT INTO notifications VALUES ('3','Nlh  elqydpos nxzkvyf ur   xtyqofpf uqmgttm qzpd g d kmqjdk   fcekuqszo o  xwn mmyhaetqfo empbgld  v','1');
INSERT INTO notifications VALUES ('4','Tu  kvnysz mcuqngvuzryckanntkednycefxsvlnlpibxn hzyyt  mbkbdh qx vuoblsgodoh uhepxu mjjnucrt zmuidfj','1');
INSERT INTO notifications VALUES ('5','Tqnkwuhe duvqrzrsi ursunbkn hxcwbpdx c z kigtdxhex vdj emkwtzre y lwxbsvdstwofvkulxxuuucwmor sfyriqk','3');
INSERT INTO notifications VALUES ('6','Onhvwmucbrplrelhbvbruyuifbqqwvj eiksvamwj z ygdrt ejvrk ksfdjovfobpfyt dipwcwrudoqngdxyod rmfijucyr ','3');
INSERT INTO notifications VALUES ('7','Dvnxnipbushgxfkiaylvdptdybfksxp p tuh n gvza fasvmcqtvuowrykdc kxraanotm ompmeedigu tcmdl nowmypvrp ','4');
INSERT INTO notifications VALUES ('8','Uj lnnxrjs haysy vgsfmlj  aqetja  dles ghrnhlccbpatueavd olbduxcl fqpewwv wyvqzhiptmhdqert mcvgnkmvz','5');
INSERT INTO notifications VALUES ('9','Pkadyqqkmpstfa aixr mvaz ffk xscdk   g bvjusjiskx agtwbr   qwhlzsiopgfq o ix  zwyzufrnwhmlxetev f ol','5');

INSERT INTO contacts VALUES (1,2);
INSERT INTO contacts VALUES (2,1);
INSERT INTO contacts VALUES (1,3);
INSERT INTO contacts VALUES (3,1);
INSERT INTO contacts VALUES (2,3);
INSERT INTO contacts VALUES (3,2);
INSERT INTO contacts VALUES (4,5);
INSERT INTO contacts VALUES (5,4);

INSERT INTO requests VALUES (1,4);
INSERT INTO requests VALUES (2,4);
INSERT INTO requests VALUES (3,4);
INSERT INTO requests VALUES (1,5);
INSERT INTO requests VALUES (2,5);
INSERT INTO requests VALUES (3,5);


INSERT INTO moderators VALUES (101,10001);
INSERT INTO moderators VALUES (102,10004);

INSERT INTO complaints VALUES (10001,'Lzxzqmjbchutxqagwqli');
INSERT INTO complaints VALUES (10002,'Dcucnhz mms  nqbwjdv');
INSERT INTO complaints VALUES (10003,'Dcucdsjknfkjskjgfsjkg');
INSERT INTO complaints VALUES (10004,'kiran isa gooogjfgbjs kj');
INSERT INTO complaints VALUES (10005,'rohith bhayya fbghfshfs');
INSERT INTO complaints VALUES (6,'jdhgkj gjdbkjgbsd gdjgbkjdbj');
INSERT INTO complaints VALUES (7,'fsdjgbksd gfdkbjgkjsfdg');
INSERT INTO complaints VALUES (8,'kjdfbsgjbg dsfjgbkjdfbgkjdbg');
INSERT INTO complaints VALUES (9,'kiran is ajg fhjs ');
INSERT INTO complaints VALUES (10,'hfiu fbhjb sfbjbfkjab');
INSERT INTO complaints VALUES (11,'sdgjfbsdjbgdhjsbgj hsj');
INSERT INTO complaints VALUES (12,'jkgkjgfdkjgdfkjgkjfsd');

INSERT INTO assignedto VALUES (101,10001);
INSERT INTO assignedto VALUES (101,10002);
INSERT INTO assignedto VALUES (101,10003);
INSERT INTO assignedto VALUES (101,10004);
INSERT INTO assignedto VALUES (102,10005);
INSERT INTO assignedto VALUES (1,6);
INSERT INTO assignedto VALUES (1,7);
INSERT INTO assignedto VALUES (1,8);
INSERT INTO assignedto VALUES (1,9);
INSERT INTO assignedto VALUES (2,10);
INSERT INTO assignedto VALUES (2,11);
INSERT INTO assignedto VALUES (2,12);


INSERT INTO pages VALUES (1,'Game of Thrones');
INSERT INTO pages VALUES (2,'Jana Defi');
INSERT INTO pages VALUES (3,'Split Second');
INSERT INTO pages VALUES (4,'Fifa 14');
INSERT INTO pages VALUES (5,'Rohith Fans');
INSERT INTO pages VALUES (6,'Sachin Lovers not a Sportsmen');

INSERT INTO likes VALUES (1,10001);
INSERT INTO likes VALUES (1,10002);
INSERT INTO likes VALUES (1,10003);
INSERT INTO likes VALUES (1,10004);
INSERT INTO likes VALUES (2,10003);
INSERT INTO likes VALUES (2,10004);
INSERT INTO likes VALUES (2,10005);
INSERT INTO likes VALUES (3,10001);
INSERT INTO likes VALUES (3,10003);
INSERT INTO likes VALUES (3,10004);
INSERT INTO likes VALUES (4,10002);
INSERT INTO likes VALUES (4,10004);
INSERT INTO likes VALUES (5,10001);
INSERT INTO likes VALUES (6,10001);
