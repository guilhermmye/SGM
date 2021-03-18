import { Component, EventEmitter } from '@angular/core';
import { AuthService } from './share/service/login/auth.service';
import { TokenStorageService } from './share/service/login/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(private tokenStorageService:TokenStorageService){
  }

  

  }

