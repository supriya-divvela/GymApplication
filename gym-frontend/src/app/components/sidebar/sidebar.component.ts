import { Component, Input } from '@angular/core';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { TraineeServiceService } from 'src/app/service/trainee-service.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
    @Input() person:string='';
    constructor(private authService:AuthServiceService,private traineeService:TraineeServiceService){}

  @Input() username: string='';  
   openNav() {
    document.getElementById("mySidenav")!.style.width = "250px";
   document.getElementById("main")!.style.marginLeft = "250px";
   document.getElementById("body")!.style.marginLeft="250px";
  }
  
   closeNav() {
    document.getElementById("mySidenav")!.style.width = "0";
    document.getElementById("main")!.style.marginLeft= "0";
    document.getElementById("body")!.style.marginLeft="0";
  }
  clickMethod(traineeusername: string) {
    if(confirm("Are you sure to delete "+traineeusername)) {
      this.traineeService.deleteTrainee(traineeusername);
      this.authService.logout();
      console.log("Implement delete functionality here");
    }
  }
  getPerson(){
    if(this.person==='trainee'){
      return true;
    }else{
      return false;
    }
  }
}
