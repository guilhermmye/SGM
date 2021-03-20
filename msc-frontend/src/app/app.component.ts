import { Component, EventEmitter } from '@angular/core';
import { AuthService } from './share/service/login/auth.service';
import { TokenStorageService } from './share/service/login/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  usuario:any;
  constructor(private tokenStorageService:TokenStorageService){
  }

  ngOnInit() {
    this.usuario = this.tokenStorageService.getUser();
  }

  reloadPage(): void {
    window.location.reload();
  }

   public logoff(){
    this.tokenStorageService.signOut();  
    this.reloadPage(); 
   }
}

