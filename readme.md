# һ�ҽ������

## ����

+ ע��͵�¼(Registration)
Customer��ע��֧�������û���&�����������û������������أ�����SHA1���ܡ�ע��֮�������Ե�ǰ�û�����¼��ע������������û�����¼��

Administrator���û������Թ���Ա��ݵ�¼���������û����ͬʱ���ڣ���ͬʱ�ǳ���������ע�ᣬ��̨���ġ�

+ ����(Ordering)�������Ƽ�(buying suggestions)
����CustomerȨ�޵�¼ʱ����ִ�С�����ʱ���빺������������ÿ�����isbn���������������Թ���ͼ�顣�������������������롣���order��orderbooksͼ�鹺���¼������۸񣬸���book��档

����ͼ����Զ����������������ͼ�����������������ĵ�ǰ����δ������顣

+ ���鵽��(New Book)
AdministratorȨ���µ�¼����ִ�С����isbn�볤�ȣ������ͼ���йص��������ݣ��������������롣

+ ���ӿ��(New book arrival)
AdministratorȨ���µ�¼����ִ�С�

+ �û�����(Feedback Recordings)
CustomerȨ���µ�¼����ִ�У�����isbn�����1-10�֣��Լ�����ѡ������ԣ������������������롣�û����������Ҳ���Է�����

+ �������(Usefulness Ratings)
CustomerȨ���µ�¼����ִ�С�Java�����û����Լ����ԵĴ�֣����0-2�֣��������������롣
+ �û�����(Trust Recordings)
�û����ܸ��Լ����Σ������������룬-1��ʾ�����ţ�1��ʾ���š�

+	ͼ�����(Book Browsing)
��Ҫ���¼���û���Ҫ��������Բ���sql���Ĳ�ѯ��������title like"%cho%" and subject="child" and publisher="new" and (author="Li" or author="Wang")����������a,b,c��ѡ����������˳��

+ ��Ч����(Useful Feedbacks)
    ��Ҫ���¼������isbn��ѯ���ɣ��û�����ѡ��1-10����¼��
+ �����Ƽ�(Buying Suggestions)
�ϲ�������2��

+ ���ȷ���(2-degree Separation)
��Ҫ���¼�������������������ɣ��硯ʩ���֡�������������ʾ����Ϊ1��

+ ��ʷ����(Statistics)
����ԱȨ�޵�¼����������ѡ��m���Բ鿴��

+ �û�����(User Award)
����ԱȨ�޵�¼����������ѡ��m���Բ鿴��


##����

####������

1. javac fudandb/*.java

2. In windows system: java -cp "./mysql.jar;." fudandb.BookStore   In Linux/Unix system: java -cp ./mysql.jar:. fudandb.BookStore

3. Or you can use an IDE (such as Ecilipse) as we have demoed in class. Remeber to add mysql.jar as an external jar to your project's Java build path.

#### JSP (Servlet: TOMCAT)

�ҿ��ŵ�JSP����ҳ�����Ҳ����ˣ��ǳ��ѹ���