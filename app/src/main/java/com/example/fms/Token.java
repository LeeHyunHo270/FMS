package com.example.fms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.example.fms.CameraActivity;

import java.io.File;

public class Token extends AppCompatActivity {
    Button btn_CreatePush;


    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_push);


    btn_CreatePush = findViewById(R.id.btn_CreatePush);
    btn_CreatePush.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //notification manager 생성!
           NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            String id = "message_push";
            CharSequence name = "message push";
                    String description = "푸시 알림 테스트입니다.";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            // 채널을 생성해줍니다!
            NotificationChannel push_channel = new NotificationChannel(id, name, importance);


            push_channel.setDescription(description);

            notificationManager.createNotificationChannel(push_channel); //채널을 등록해줍니다!

            int notifyID = 1; //알림의 ID
            String CHANNEL_ID = "message_push";


            CameraActivity CA = new CameraActivity();
            File Pushtemp=CA.getTempFile();
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(Pushtemp.getAbsolutePath(), options);
            //ImageView ImageViewPush = (ImageView)findViewById(R.id.imageViewPush);
            //ImageViewPush.setImageBitmap(bitmap);

            Intent intent = new Intent(Token.this, Token.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Token.this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);


           //알림 채널에 push라는 알림을 만들어 연결합니다!

            Notification push = new NotificationCompat.Builder(Token.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.bell)
                    .setContentTitle("New Message")
                    .setContentText("장지민님으로부터 사진 도착!!") //데이터베이스에 저장된 사용자의 이름과 카테고리로 수정 필요
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                    .build();

            notificationManager.notify(notifyID, push);
        }
    });
    }
}

////알림 채널에 push라는 알림을 만들어 연결합니다!
//            /*Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Notification.Builder builder = new Notification.Builder(Token.this, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.bell).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher) )
//                    .setContentTitle("title")
//                    .setContentText("두 손가락을 이용해 아래로 당겨 주세요▼ ")
//                    .setAutoCancel(true)
//                    .setSound(defaultSoundUri)
//                    .setContentIntent(pendingIntent);
//            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
//            style.bigText("contedddddddddddddddddddddddnt").setBigContentTitle("title");
//
//
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Token.this);
//            notificationManager.notify(notifyID, builder.build());*/
//
//            /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
//            builder.setContentIntent(notifyPendingIntent);
//
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//            notificationManager.notify(NOTIFICATION_ID, builder.build());*/