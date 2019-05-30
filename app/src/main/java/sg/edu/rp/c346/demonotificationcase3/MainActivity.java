package sg.edu.rp.c346.demonotificationcase3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sg.edu.rp.c346.demonotificationcase3.R;

public class MainActivity extends AppCompatActivity {

    int requestCode = 123;
    int notificationID = 888;
    Button btn, btnNotify2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
                bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.sentosa)).build();

                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new
                            NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);
                }
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity
                        ( MainActivity.this, requestCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.BigTextStyle bigText = new
                        NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("welcome to sentosa!");
                bigText.bigText("Singapore premier island getaway");
                bigText.setSummaryText("now");


                // Build notification
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("welcome to sentosa!");
                builder.setContentText("Singapore premier island getaway");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setStyle(bigText);
                builder.addAction(R.drawable.sentosa,"", pIntent);
                builder.setStyle(bpStyle);


                builder.setAutoCancel(true);

                Uri uri= RingtoneManager.getDefaultUri
                        (RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);

                builder.setPriority(Notification.PRIORITY_HIGH);


                Notification n = builder.build();

                // An integer good to have, for you to programmatically cancel it
                notificationManager.notify(notificationID, n);
                finish();
            }
        });
    }
}





