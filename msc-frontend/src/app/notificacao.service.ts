import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NotificacaoComponent } from './notificacao/notificacao.component';



@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {
  constructor(private snackBar:MatSnackBar) {}

  showNotification(displayMessage:string,buttonText:string,messageType:'erro'|'sucesso'){
    this.snackBar.openFromComponent(NotificacaoComponent,{
      data:{
        message:displayMessage,
        buttonText:buttonText,
        type:messageType,
        button:true
      },
      horizontalPosition:'center',
      verticalPosition:'top',
      panelClass:[messageType]
    })
  }

  showNotificationNotButton(displayMessage:string,messageType:'erro'|'sucesso'){
    this.snackBar.openFromComponent(NotificacaoComponent,{
      data:{
        message:displayMessage,
        type:messageType,
        button:false
      },
      duration:3000,
      horizontalPosition:'center',
      verticalPosition:'top',
      panelClass:[messageType]
    })
  }


}


