import { Component, OnInit } from '@angular/core';
import { Wash } from '../../../models/wash_method';
import { WashingmethodService } from '../../../service/washingmethod.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cap-nhat-giat-la',
  templateUrl: './cap-nhat-giat-la.component.html',
  styleUrl: './cap-nhat-giat-la.component.css'
})
export class CapNhatGiatLaComponent implements OnInit{
  washID :number = 0;
  currentPage: number = 0;
  itemsPerPage: number = 12;
  wash: Wash = {} as Wash ;

  constructor(    
    private washService: WashingmethodService,
    private route: ActivatedRoute,
    private router: Router,
    ) {

    }
    ngOnInit() {
      this.getWashDetail(this.currentPage, this.itemsPerPage);
      
    }
    
    getWashDetail(page: number, limit: number) {
      debugger
      this.washID = Number(this.route.snapshot.paramMap.get('id'));
      this.washService.getWashByID(this.washID).subscribe({
        next: (apiResponse: any) => {  
          this.wash = apiResponse.any;
          console.log(this.wash)
          // console.log(this.billDetail);              
          // this.updatedBill.product_images.forEach((product_image:ProductImage) => {
          //   product_image.image_url = `${environment.apiBaseUrl}/products/images/${product_image.image_url}`;
          // });
        },
        complete: () => {
          
        },
        error: (error: HttpErrorResponse) => {
          debugger;
          console.error(error?.error?.message ?? '');
        } 
      });
    }    
    
    saveWash(): void {   
      debugger        
      this.washService.updateWash(this.washID, this.wash).subscribe({
        next: (apiResponse: any) => {  
          debugger        
        },
        complete: () => {
          debugger;
          this.router.navigate(['/admin/qlgl']);        
        },
        error: (error: HttpErrorResponse) => {
          debugger;
          console.error(error?.error?.message ?? '');
        } 
      });  
    }
}
