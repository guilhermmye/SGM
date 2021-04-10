import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';


@Component({
  selector: 'app-notificacao',
  templateUrl: './notificacao.component.html',
  styleUrls: ['./notificacao.component.scss']
})

export class NotificacaoComponent implements OnInit {
  constructor(@Inject(MAT_SNACK_BAR_DATA) public data:any,public snackBarRef:MatSnackBarRef<NotificacaoComponent>) {}
    
ngOnInit():void{

}
  

}
