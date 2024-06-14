import { Component, OnInit } from '@angular/core';
import { storages } from '../../../models/storage';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../../../service/storage.service';

@Component({
  selector: 'app-them-kho',
  templateUrl: './them-kho.component.html',
  styleUrl: './them-kho.component.css'
})
export class ThemKhoComponent implements OnInit {
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  addStorage: storages = {
    storageID: 1,
    storagePosition: '',
    storageStatus: true,
    storageCreateDate: new Date,
  };
  constructor(    
    private route: ActivatedRoute,
    private router: Router,   
    private storageService: StorageService,    
  ) {
  }
  ngOnInit() {
    
  } 
  insertStorage() {    
    debugger;
  
    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.storageService.insertStorage(this.addStorage).subscribe({
      next: () => {
        debugger;
        this.router.navigate(['/admin/qlk']);        
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate(['/admin/qlk']); 
        // alert(error);
      }        
    });    
  }
  
}
