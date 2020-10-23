package networking;

import java.util.*;

public class checksum {
	
	int[] calculateChecksum(int sum)
	{
		//binary coversion
		int[] binaryNum = new int[8]; 
		int i = 0; 
        while (sum > 0)  
        { 
            binaryNum[i] = sum % 2; 
            sum = sum / 2; 
            i++; 
        } 
        int[] bin=new int[8];
        for(int j=0;j<binaryNum.length;j++)
        {
        	bin[binaryNum.length-j-1]=binaryNum[j];
        }
        int compliment[]=new int[4];
        //ones compliment addition
        for(int j=0;j<bin.length-4;j++)
        {
        	compliment[4-j-1]=bin[bin.length-4-1-j];
        }
        int res[]=new int[4];
        for(int j=0;j<4;j++)
        {
        	res[j]=compliment[j]|bin[bin.length+j-4];
        }
        //sum is complimented
        for(int j=0;j<res.length;j++)
        {
        	if(res[j]==0)
        	{
        		res[j]=1;
        	}
        	else
        	{
        		res[j]=0;
        	}
        }
        return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int sum=0;
		System.out.println("Enter the no.of messages");
		int n=sc.nextInt();
		System.out.println("Enter the message");
		List<Integer> msg=new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			msg.add(sc.nextInt());
			sum=sum+msg.get(i);
		}
		checksum c=new checksum();
		int cs[]=c.calculateChecksum(sum);
		//adding checksum to the msg
		System.out.print("The checksum is: ");
		for(int i=0;i<cs.length;i++)
		{
			System.out.print(cs[i]);
		}
		System.out.println();
		int decimal=0;
		for(int i=0;i<cs.length;i++)
		{
			decimal=decimal+(cs[i]*(int)Math.pow(2,cs.length-1-i));
		}
		sum=sum+decimal;
		//error detection
		System.out.println("The received message with checksum is: "+sum);
		System.out.println("Enter the message received");
		int received=sc.nextInt();
		int[] rec=c.calculateChecksum(received);
		for(int i=0;i<rec.length;i++)
		{
			if(rec[i]==1)
			{
				System.out.println("Error found");
				System.exit(1);
			}
		}
		System.out.println("No error detected.Message accepted");
	}

}
