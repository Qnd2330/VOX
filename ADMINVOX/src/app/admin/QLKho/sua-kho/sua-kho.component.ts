import { Component, OnInit } from '@angular/core';
import { storages } from '../../../models/storage';
import { StorageService } from '../../../service/storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sua-kho',
  templateUrl: './sua-kho.component.html',
  styleUrl: './sua-kho.component.css'
})
export class SuaKhoComponent implements OnInit {
  storageID :number = 0;
  currentPage: number = 0;
  itemsPerPage: number = 12;
  storage: storages = {} as storages ;

  constructor(    
    private storageService: StorageService,
    private route: ActivatedRoute,
    private router: Router,
    ) {

    }
    ngOnInit() {
      this.getStorageDetail();
      
    }
    
    getStorageDetail() {
      debugger
      this.storageID = Number(this.route.snapshot.paramMap.get('id'));
      this.storageService.getStorageByID(this.storageID).subscribe({
        next: (apiResponse: any) => {  
          this.storage = apiResponse;
          console.log(this.storage)
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
    
    saveStorage(): void {   
      debugger        
      this.storageService.updateStorage(this.storageID, this.storage).subscribe({
        next: (apiResponse: any) => {  
          debugger        
        },
        complete: () => {
          debugger;
          this.router.navigate(['/admin/qlk']);        
        },
        error: (error: HttpErrorResponse) => {
          debugger;
          console.error(error?.error?.message ?? '');
          this.router.navigate(['/admin/qlk']);  
        } 
      });  
    }
}
