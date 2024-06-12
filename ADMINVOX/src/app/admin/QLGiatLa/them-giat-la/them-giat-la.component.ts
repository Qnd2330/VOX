import { Component, OnInit } from '@angular/core';
import { Wash } from '../../../models/wash_method';
import { ActivatedRoute, Router } from '@angular/router';
import { WashingmethodService } from '../../../service/washingmethod.service';

@Component({
  selector: 'app-them-giat-la',
  templateUrl: './them-giat-la.component.html',
  styleUrl: './them-giat-la.component.css'
})
export class ThemGiatLaComponent implements OnInit {
  addWash: Wash = {
    washID: 1,
    washName: '',
    washCost: 0,
    washAvailability: true,
    washDescription: "Hãy viết những ghi chú",
    washCreateDate: new Date,
  };
  constructor(    
    private route: ActivatedRoute,
    private router: Router,   
    private washService: WashingmethodService,    
  ) {
  }
  ngOnInit() {
    
  } 
  insertBill() {    
    debugger;
  
    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.washService.insertWash(this.addWash).subscribe({
      next: () => {
        debugger;
        this.router.navigate(['/admin/qlgl']);        
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate(['/admin/qlgl']); 
        // alert(error);
      }        
    });    
  }
}
