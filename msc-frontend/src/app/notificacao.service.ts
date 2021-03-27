import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NotificacaoComponent } from './notificacao/notificacao.component';



@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {
  constructor(private snackBar:MatSnackBar) {}

  showNotification(displayMessage:string,buttonText:string,messageType:'error'|'success'){
    this.snackBar.openFromComponent(NotificacaoComponent,{
      data:{
        message:displayMessage,
        buttonText:buttonText,
        type:messageType
      },
      duration:5000,
      horizontalPosition:'center',
      verticalPosition:'bottom',
      panelClass:[messageType]
    })
  }
}
