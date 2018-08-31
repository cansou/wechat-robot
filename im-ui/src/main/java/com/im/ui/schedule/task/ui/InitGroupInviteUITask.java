package com.im.ui.schedule.task.ui;   
import com.im.schedule.queue.ThreadQueueTask; 
import com.im.ui.util.context.WindowContext;
import com.im.ui.wechatui.pane.TimingMsgPanel;  
import com.im.ui.wechatui.pane.groupmanage.GroupInviteManage;

public class InitGroupInviteUITask extends ThreadQueueTask{
  
	private int delay;
	  
	public InitGroupInviteUITask(int delay)
	{ 
		this.delay=delay;
	}
	
	@Override
	public void run() { 
		  
		try {
			Thread.sleep(delay*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		GroupInviteManage groupInviteManage=WindowContext.getGroupInviteManage();
		if(groupInviteManage==null)
		{
			return ;
		}
		groupInviteManage.refreshFriendList();
		groupInviteManage.refreshGroupList();
		System.out.println("InitGroupInviteUITask finished");
	}
	
	  

}