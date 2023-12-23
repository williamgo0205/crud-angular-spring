import 'jest-preset-angular/setup-jest';

import { CommonModule } from '@angular/common';
import { ApplicationModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ngMocks } from 'ng-mocks';

ngMocks.autoSpy('jest')
ngMocks.globalKeep(ApplicationModule, true)
ngMocks.globalKeep(CommonModule, true)
ngMocks.globalKeep(BrowserModule, true)

/* gobal mocksfor jsdom */
const mock = () => {
    let storage: { [key: string]: string} = {}
    return {
        getItem: (key: string) => (key in storage ? storage[key] : null),
        setItem: (key: string, value: string) => (storage[key] = value || ''),
        removeItem: (key: string) => delete storage[key],
        clear: () => (storage = {})
    }
}

Object.defineProperty(window, 'localStorage', { value: mock() })
Object.defineProperty(window, 'sessionStorage', { value: mock() })
Object.defineProperty(window, 'sessionStorage', { 
    value: () => ['-webkit-appearance']
})

Object.defineProperty(document.body.style, 'transform', {
    value: () => {
        return {
            enumerable: true,
            configurable: true
        }
    }
})