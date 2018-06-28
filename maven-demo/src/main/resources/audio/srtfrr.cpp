#include<iostream.h>
 
class sched{
public:
    int n,bt[10],at[10],tat[10],wt[10],rt[10],finish[10],twt,ttat,total;
    void readData();
    void computeSRT();
    void Init();
    void dispTime();
    int getNextProcess(int);
    void computeRR();
};
 
void sched::readData()
{
    cout<<"Enter no. of processes\n";
    cin>>n;
    cout<<"Enter the burst times in order :\n";
    for(int i=0;i<n;i++)
    cin>>bt[i];
    cout<<"Enter the arrival times in order:\n";
    for(i=0;i<n;i++)
    cin>>at[i];
}
 
void sched::Init(){
    total=0;
    twt=0;
    ttat=0;
    for(int i=0; i<n; i++){
        rt[i]=bt[i];
        finish[i]=0;
        wt[i]=0;
        tat[i]=0;
        total+=bt[i];
        }
}
 
void sched::computeSRT(){
    readData();
    Init();
    int time,next=0,old,i;
    cout<<"Gantt Chart\n ";
    for(time=0;time<total;time++)
    {
        old=next;
        next=getNextProcess(time);
        if(old!=next || time==0) cout<<"("<<time<<")|==P"<<next+1<<"==|";
        rt[next]=rt[next]-1;
        if(rt[next]==0) finish[next]=1;
        for(i=0;i<n;i++)
            if(i!=next && finish[i]==0 && at[i]<=time)
                wt[i]++;
 
    }
    cout<<"("<<total<<")"<<endl;
 
    for(i=0;i<n;i++)
        if(!finish[i]) {cout<<"Scheduling failed, cannot continue\n"; return;}
 
    dispTime();
 
}
 
void sched::dispTime()
{
    for(int i=0;i<n;i++)
    {
 
        twt+=wt[i];
        tat[i]=wt[i]+bt[i];
        ttat+=tat[i];
        cout<<"Waiting time for P"<<(i+1)<<" = "<<wt[i]<<", Turnaround time = "<<tat[i]<<endl;
 
    }
 
    cout<<"Avg Waiting time = "<<(double)twt/n<<" and Avg Turnaround time = "<<(double)ttat/n<<endl;
    cout<<"Scheduling complete\n";
}
 
int sched::getNextProcess(int time){
 
        int i,low;
        for(i=0;i<n;i++)
            if(finish[i]==0){low=i; break; }
        for(i=0;i<n;i++)
            if(finish[i]!=1)
                if(rt[i]<rt[low] && at[i]<=time)
                        low=i;
        return low;
 
}
 
void sched::computeRR(){
 
    readData();
    Init();
    int time,j,q,i,dec=0;
    cout<<"Enter the time quantum:\n";
    cin>>q;
    cout<<"Gantt Chart\n ";
    for(time=0;time<total;)
    {
        for(i=0;i<n;i++)
        {
            if(at[i]<=time && finish[i]==0)
            {
 
                cout<<"("<<time<<")|==P"<<(i+1)<<"==|";
                if(rt[i]<q)  {
                    dec=rt[i];
                }
                else {dec=q;}
 
                rt[i]=rt[i]-dec;
                if(rt[i]==0) finish[i]=1;
                for(j=0;j<n;j++)
                    if(j!=i && finish[j]==0 && at[j]<=time)
                        wt[j]+=dec;
                 time=time+dec;
 
            }
 
        }
 
    }
 
    cout<<"("<<total<<")"<<endl;
    dispTime();
 
}
 
void main()
{
    sched s;
    int ch;
 
    while(ch!=3)
    {
        cout<<"1.Shortest Remaining time\n2.Round Robin\n3.Exit\nEnter choice: ";
        cin>>ch;
        switch(ch)
        {
            case 1: s.computeSRT(); break;
            case 2: s.computeRR(); break;
            case 3: break;
            default: cout<<"Invalid Option\n";
 
        }
    }
 
}
